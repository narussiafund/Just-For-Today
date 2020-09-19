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

class JFTFragment : Fragment() {

    private val viewModel: JFTViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jft_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadDairy()
        viewModel.getDairy().observe(this as LifecycleOwner) { dairy ->
            textTitle.text = dairy.title
        }
    }
}
