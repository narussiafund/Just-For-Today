package org.narussia.justfortoday.jft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.jft_fragment.*
import org.narussia.justfortoday.R
import org.narussia.justfortoday.utils.fromHtml

class JFTFragment : Fragment() {

    private val viewModel: JFTViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jft_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDairy().observe(this as LifecycleOwner) { dairy ->
            textDate.text = dairy.date
            textTitle.text = dairy.title.fromHtml()
            textDayText.text = dairy.daytext.fromHtml()
            textBaseText.text = dairy.basetext.substringBefore('<', "").fromHtml()
            textBaseTextReference.text = dairy.basetext.substringAfter('>', "").fromHtml()
            textJustForToday.text = String.format(getString(R.string.just_for_today_bottom), dairy.justfortoday)
        }
    }
}
