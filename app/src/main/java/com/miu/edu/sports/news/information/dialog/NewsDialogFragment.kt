package com.miu.edu.sports.news.information.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.databinding.NewsDialogBinding
import com.miu.edu.sports.news.information.model.NewsModel
import com.miu.edu.sports.news.information.utils.onErrorTextFieldValidator
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty

class NewsDialogFragment(private val itemClicked: OnAddNewsItemClicked) : AppCompatDialogFragment(), Validator.ValidationListener {
    private lateinit var binding: NewsDialogBinding
    private lateinit var validator: Validator

    @NotEmpty(message = "Image Url Field is required")
    lateinit var newsImageEditTextView: TextInputEditText

    @NotEmpty(message = "News Title Field is required")
    lateinit var titleEditText: TextInputEditText

    @NotEmpty(message = "News Description Field is required")
    lateinit var descriptionEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * Initializing fields
        * */
        initializingFields()

        validator = Validator(this)
        validator.setValidationListener(this)

        binding.addNewsTextView.setOnClickListener {
            validator.validate()
        }

        binding.cancelTextView.setOnClickListener {
            dismiss()
        }
    }

    private fun initializingFields() {
        newsImageEditTextView = binding.newsImageUrl
        titleEditText = binding.newsTitleEditText
        descriptionEditText = binding.newsDescriptionInputEditText
    }

    override fun onValidationSucceeded() {
        val imageUrl = binding.newsImageUrl.text.toString()
        val title = binding.newsTitleEditText.text.toString()
        val description = binding.newsDescriptionInputEditText.text.toString()
        itemClicked.onNewsAdded(NewsModel(title,description,imageUrl), dialog)
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        requireActivity().onErrorTextFieldValidator(errors)
    }
}

interface OnAddNewsItemClicked{
    fun onNewsAdded(newsModel: NewsModel, dialog: Dialog?)
}