package com.droidios.foodrecipe.presentation.meal_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.droidios.foodrecipe.R
import com.droidios.foodrecipe.databinding.FragmentMealDetailsBinding
import com.droidios.foodrecipe.domain.model.MealDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MealDetailsFragment : Fragment(R.layout.fragment_meal_details) {

    private val binding by viewBinding(FragmentMealDetailsBinding::bind)
    private val args: MealDetailsFragmentArgs by navArgs()
    private val viewModel: MealDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.mealId?.let {
            viewModel.getDetailsMealList(it)
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealDetailsState.collect {
                if (it.isLoading) {
                    binding.progressBar.isVisible = true
                }
                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    uiBind(it)
                    binding.progressBar.isVisible = false
                }
            }
        }
//        binding.detailsBackArrow.setOnClickListener {
//            findNavController().popBackStack()
//        }
    }

    private fun uiBind(it: MealDetails) {
        with(binding) {
            textViewName.text = it.name
            textViewCategory.text = it.category
            textViewInstructions.text = it.instructions
            textViewIngredient1.text = it.ingredient1
            textViewMeasure1.text = it.measure1
            textViewIngredient2.text = it.ingredient2
            textViewMeasure2.text = it.measure2
            textViewIngredient3.text = it.ingredient3
            textViewMeasure3.text = it.measure3
            textViewIngredient4.text = it.ingredient4
            textViewMeasure4.text = it.measure4
            textViewIngredient5.text = it.ingredient5
            textViewMeasure5.text = it.measure5
            textViewIngredient6.text = it.ingredient6
            textViewMeasure6.text = it.measure6
            textViewIngredient7.text = it.ingredient7
            textViewMeasure7.text = it.measure7
            textViewIngredient8.text = it.ingredient8
            textViewMeasure8.text = it.measure8
            textViewIngredient9.text = it.ingredient9
            textViewMeasure9.text = it.measure9
            textViewIngredient10.text = it.ingredient10
            textViewMeasure10.text = it.measure10
            textViewIngredient11.text = it.ingredient11
            textViewMeasure11.text = it.measure11
            textViewIngredient12.text = it.ingredient12
            textViewMeasure12.text = it.measure12
            textViewIngredient13.text = it.ingredient13
            textViewMeasure13.text = it.measure13
            textViewIngredient14.text = it.ingredient14
            textViewMeasure14.text = it.measure14
            textViewIngredient15.text = it.ingredient15
            textViewMeasure15.text = it.measure15
            Glide.with(root)
                .load(it.image)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imageView)
        }
    }
}