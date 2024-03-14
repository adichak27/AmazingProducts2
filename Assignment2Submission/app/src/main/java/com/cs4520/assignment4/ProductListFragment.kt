package com.cs4520.assignment4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs4520.assignment4.databinding.FragmentProductListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProductAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // observe live data from view model and update adapter
        viewModel.products.observe(viewLifecycleOwner) { products ->
            binding.progressBar.visibility = View.GONE
            if (products.isNotEmpty()) {
                adapter.submitList(products)
                binding.recyclerView.visibility = View.VISIBLE
                binding.textViewEmpty.visibility = View.GONE
            } else {
                binding.recyclerView.visibility = View.GONE // Hide the RecyclerView
                binding.textViewEmpty.visibility = View.VISIBLE
            }
        }
        viewModel.fetchProducts() // Fetch products from the repository
    }
}