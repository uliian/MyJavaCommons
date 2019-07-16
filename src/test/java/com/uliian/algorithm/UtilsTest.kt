package com.uliian.algorithm

import com.uliian.utils.toDate
import org.junit.Test
import java.time.LocalDateTime

class UtilsTest {
    @Test fun dateTest(){
        LocalDateTime.now().toDate()
    }
}
