package com.droidios.foodrecipe.presentation.meal_search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.droidios.foodrecipe.R
import com.droidios.foodrecipe.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MealSearchFragment : Fragment(R.layout.fragment_meal_search) {

    private val binding by viewBinding(FragmentMealSearchBinding::bind)
    private val viewModel: MealSearchViewModel by viewModels()
    private var _adapter: MealSearchAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mealSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchMealList(it)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        binding.mealSearchRecycler.apply {
            _adapter = MealSearchAdapter()
            adapter = _adapter
            setHasFixedSize(true)
            setClick()
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealSearchState.collect {
                if (it.isLoading) {
                    binding.progressMealSearch.visibility = View.VISIBLE
                    binding.nothingFound.visibility = View.GONE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                }
                it.data?.let {
                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    _adapter?.differ?.submitList(it.toMutableList())
                }
            }
        }
    }

    private fun setClick() {
        _adapter?.onClickListener = {
            val nav = MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(
                mealId = it.mealId
            )
            findNavController().navigate(nav)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _adapter = null
    }
}