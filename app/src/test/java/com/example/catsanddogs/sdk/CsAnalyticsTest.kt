package com.example.catsanddogs.sdk

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.mockito.Mockito
import kotlin.test.Test
import kotlin.test.assertEquals

class CsAnalyticsTest {

    private class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val itemView = Mockito.mock(View::class.java)
    private val context = Mockito.mock(Context::class.java)
    private val cs = CsAnalytics(context)

    @Test
    fun `track function can track pet type, position and number of pets`() {
        // given
        val viewHolder = TestViewHolder(itemView)
        Mockito.`when`(itemView.contentDescription).thenReturn(CsAnalytics.CAT)

        // when
        cs.track(viewHolder, 0)
        cs.track(viewHolder, 1)
        cs.track(viewHolder, 2)

        // then
        val pet = cs.petList[2]
        assertEquals(pet.petType, CsAnalytics.CAT)
        assertEquals(pet.numberOfPets, 3)
    }

    @Test
    fun `trigger function uses the right message in its callable field`() {
        // given
        val viewHolder = TestViewHolder(itemView)
        Mockito.`when`(itemView.contentDescription).thenReturn(CsAnalytics.CAT)
        cs.track(viewHolder, 0)

        // when
        cs.trigger(viewHolder, 0)

        // then
        assertEquals(cs.callable.message, "Position 0. There is 1 cat.")
    }
}