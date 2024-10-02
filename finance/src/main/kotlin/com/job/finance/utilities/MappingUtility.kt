package com.job.finance.utilities

class MappingUtility {
    companion object {
        fun convertToMap(any: Any?): Map<String, Any> {
            return when (any) {
                is Map<*, *> -> {
                    // Safe cast to Map<String, Any>
                    @Suppress("UNCHECKED_CAST")
                    any as Map<String, Any>
                }
                null -> {
                    // Handle null case
                    emptyMap() // Return an empty map for null input
                }
                else -> {
                    // Handle case where `any` is not a Map
                    emptyMap() // or throw an exception, or return a default value
                }
            }
        }

    }
}