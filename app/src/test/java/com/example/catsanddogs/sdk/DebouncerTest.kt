package com.example.catsanddogs.sdk

import org.mockito.Mockito
import org.mockito.Mockito.verify
import java.util.concurrent.Callable
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DebouncerTest {

    private val callable = Mockito.mock(Callable::class.java)
    private val debouncer = Debouncer(callable)

    @BeforeTest
    fun setup() {
        Mockito.`when`(callable.call()).thenReturn(Unit)
    }

    @Test
    fun `run function calls call function of callable`() {
        // when
        debouncer.run()

        // then
        verify(callable).call()
    }

    @Test
    fun `run function reset the time to wait`() {
        // given
        debouncer.debounceTimeInMillisec = 1000
        debouncer.timeLeftToWait = 500

        // when
        debouncer.run()

        // then
        assertEquals(debouncer.timeLeftToWait, debouncer.debounceTimeInMillisec)
    }
}

