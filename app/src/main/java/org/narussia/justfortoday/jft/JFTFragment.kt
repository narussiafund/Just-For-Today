package org.narussia.justfortoday.jft

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.jft_fragment.*
import org.narussia.justfortoday.R
import java.text.SimpleDateFormat
import java.util.*

class JFTFragment : Fragment() {


    private val locale = Locale("ru", "RU")
    val cal = Calendar.getInstance()
    val df = SimpleDateFormat("d MMMM, EEEE", locale)
    val sdf = SimpleDateFormat("d.M")

    private val viewModel: JFTViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jft_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadDairy()
        viewModel.getDairy().observe(this as LifecycleOwner) { dairy ->
            textDate.text = df.format(cal.getTime())
            textTitle.text = dairy.title
            textDayText.text = Html.fromHtml(dairy.daytext)
            textBaseText.text = Html.fromHtml((dairy.basetext).substringBefore('<',""))
            textBaseTextReference.text = Html.fromHtml((dairy.basetext).substringAfter('>',""))
            textJustForToday.text = "ТОЛЬКО СЕГОДНЯ: " + dairy.justfortoday
        }
    }
}
