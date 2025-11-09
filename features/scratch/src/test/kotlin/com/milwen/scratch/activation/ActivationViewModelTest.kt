package com.milwen.scratch.activation

import com.milwen.baseline.business.ScreenState
import com.milwen.scratch.business.ActivationViewModel
import com.milwen.scratch.data.ScratchCard
import com.milwen.scratch.data.ScratchState
import com.milwen.scratch.domain.ActivationRepository
import com.milwen.scratch.domain.ScratchCardObserveUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

private class FakeObserveUseCase(
    private val upstream: MutableSharedFlow<ScratchCard>
) : ScratchCardObserveUseCase {
    override fun invoke(): Flow<ScratchCard> = upstream
}

class ActivationViewModelTest {

    private lateinit var mainDispatcher: TestDispatcher
    private lateinit var appScope: CoroutineScope
    private lateinit var repo: ActivationRepository
    private lateinit var flow: MutableSharedFlow<ScratchCard>
    private lateinit var uc: ScratchCardObserveUseCase
    private lateinit var vm: ActivationViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        mainDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(mainDispatcher)

        appScope = TestScope(SupervisorJob() + mainDispatcher)

        repo = mockk(relaxed = true)
        flow = MutableSharedFlow(replay = 1)
        uc = FakeObserveUseCase(flow)

        vm = ActivationViewModel(
            appScope = appScope,
            activationRepository = repo,
            scratchCardObserveUseCase = uc
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `canActivate is false when Unscratched`() = runTest {
        flow.emit(ScratchCard(ScratchState.Unscratched))
        advanceUntilIdle()

        assertEquals(false, vm.uiState.value.canActivate)
        assertIs<ScratchState.Unscratched>(vm.uiState.value.scratchCard.status)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `canActivate is true when Scratched`() = runTest {
        flow.emit(ScratchCard(ScratchState.Scratched(revealCode = "ABC")))
        advanceUntilIdle()

        assertEquals(true, vm.uiState.value.canActivate)
        assertIs<ScratchState.Scratched>(vm.uiState.value.scratchCard.status)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `activateCard shows Loading then clears on success and calls repository`() = runTest {
        // Prepare card to be in Scratched state
        val code = "ABC-123"
        flow.emit(ScratchCard(ScratchState.Scratched(revealCode = code)))
        advanceUntilIdle()

        // Artificial delay to see loading state
        coEvery { repo.activate(code) } coAnswers {
            delay(1000)
        }

        vm.activateCard()

        runCurrent()

        // Check loading
        assertIs<ScreenState.Loading>(vm.uiState.value.screenState)

        // Complete task
        advanceTimeBy(1000)
        advanceUntilIdle()

        // Verify UI state
        assertEquals(null, vm.uiState.value.screenState)

        // Verify Coroutine call
        coVerify(exactly = 1) { repo.activate(code) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `activateCard with missing code sets error`() = runTest {
        flow.emit(ScratchCard(ScratchState.Unscratched))
        advanceUntilIdle()

        // Run Activate Card API
        vm.activateCard()

        runCurrent()

        val st = vm.uiState.value.screenState

        // Verify if state is Error
        assertTrue(st is ScreenState.Error)
    }
}