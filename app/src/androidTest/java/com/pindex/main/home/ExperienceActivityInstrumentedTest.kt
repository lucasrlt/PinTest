package com.pindex.main.home

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pindex.main.R
import com.pindex.main.models.BlockDto
import com.pindex.main.models.BlocksWrapperDto
import com.pindex.main.models.ContentDto
import com.pindex.main.models.ExperienceDto
import com.pindex.main.models.blocks.AudioBlockDto
import com.pindex.main.models.blocks.TextBlockDto
import com.pindex.main.utils.Constants
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExperienceActivityInstrumentedTest {

    /**
     * Test Experience Activity
     */
    private lateinit var activityScenario: ActivityScenario<ExperienceActivity>

    /**
     * Experience Pindex blocks (for a single test at once)
     */
    private var blocksList: ArrayList<BlockDto> = ArrayList()

    @After
    fun tearDown() {
        activityScenario.close()

        // Empty the blocks list for the next test
        blocksList = ArrayList()
    }

    /**
     * Assert that the Experience activity is displayed.
     */
    private fun assertActivityIsDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.experience_root_layout))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * Add the given [block] to the blocks list.
     */
    private fun addBlock(block: BlockDto) {
        blocksList.add(block)
    }

    /**
     * Start the Experience Activity with the current blocks list.
     */
    private fun startActivityWithBlocksList() {
        // Recreate the Firestore Experience structure
        val blocksWrapper = BlocksWrapperDto(blocksList)
        val content = ContentDto(blocksWrapper = blocksWrapper)
        val experience = ExperienceDto(content = arrayListOf(content))

        // Create the activity intent
        val intent = Intent(ApplicationProvider.getApplicationContext(), ExperienceActivity::class.java)
        intent.putExtra(Constants.ACTIVITY_EXPERIENCE_EXTRA_NAME, experience)

        // Starts the activity
        activityScenario = ActivityScenario.launch(intent)
    }

    /**
     * Create and add an Audio block with the given [name] to the blocks list.
     */
    private fun addAudioBlock(name: String) {
        val audioBlock = AudioBlockDto(name = name)
        val block = BlockDto(audio = audioBlock, type = "audio")

        addBlock(block)
    }

    /**
     * Create and add a Big Header block with the given [title] to the blocks list.
     */
    private fun addBigHeaderBlock(title: String) {
        val textBlock = TextBlockDto(text = title)
        val block = BlockDto(text = textBlock, type = "bigHeader")

        addBlock(block)
    }

    /**
     * Create and add a Text block with the given [text] to the blocks list.
     */
    private fun addTextBlock(text: String) {
        val textBlock = TextBlockDto(text = text)
        val block = BlockDto(text = textBlock, type = "text")

        addBlock(block)
    }

    /**
     * Test that the Experience Activity given an Audio block displays this block.
     */
    @Test
    fun launchingExperienceActivityWithAudioBlockDisplaysIt() {
        val name = "Insomnia - KAS:ST"
        addAudioBlock(name = name)

        startActivityWithBlocksList()

        assertActivityIsDisplayed()

        // Assert that the Text block text is displayed
        Espresso.onView(ViewMatchers.withId(R.id.pindex_block_audio_button))
                .check(ViewAssertions.matches(ViewMatchers.withText(name)))
    }

    /**
     * Test that the Experience Activity given a Big Header block displays this block.
     */
    @Test
    fun launchingExperienceActivityWithBigHeaderBlockDisplaysIt() {
        val title = "London isn't England"
        addBigHeaderBlock(title)

        startActivityWithBlocksList()

        assertActivityIsDisplayed()

        // Assert that the Text block text is displayed
        Espresso.onView(ViewMatchers.withId(R.id.pindex_block_big_header))
                .check(ViewAssertions.matches(ViewMatchers.withText(title)))
    }

    /**
     * Test that the Experience Activity given a Text block displays this block.
     */
    @Test
    fun launchingExperienceActivityWithTextBlockDisplaysIt() {
        val text = "En fait, c'est pas si terrible que ça le testing Android..."
        addTextBlock(text)

        startActivityWithBlocksList()

        assertActivityIsDisplayed()

        // Assert that the Text block text is displayed
        Espresso.onView(ViewMatchers.withId(R.id.pindex_block_text))
                .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

}