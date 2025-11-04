object Config {

    object Android {
        const val APPLICATION_ID = "com.milwen.scratch"
        const val NAMESPACE = "com.milwen.scratch"
        const val TARGET_SDK = 36
        const val COMPILE_SDK = TARGET_SDK
        const val MIN_SDK = 29
        val VERSION_CODE = System.getenv("VERSION_CODE")?.toIntOrNull() ?: 1
        const val VERSION_NAME = "1.0.0"
    }

}