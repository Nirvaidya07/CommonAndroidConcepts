package com.nirali.auth_design.presentation


        fun String.lengthOfLongestSubstring(): Int {
            var maxLength = 0
            val charSet = mutableSetOf<Char>()
            var left = 0
            var right = 0
            while (right < length) {
                if (!charSet.contains(this[right])) {
                    charSet.add(this[right])
                    right++
                    maxLength = maxOf(maxLength, charSet.size)
                } else {
                    charSet.remove(this[left])
                    left++

                }
            }
            return maxLength
        }

