package com.miu.edu.sports.news.information.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.databinding.AthletesDialogBinding
import com.miu.edu.sports.news.information.model.AthletesModel
import com.miu.edu.sports.news.information.utils.onErrorTextFieldValidator
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty

class AthletesDialogFragment(private val itemClicked: OnAddAthletesItemClicked) : AppCompatDialogFragment(), Validator.ValidationListener {
    private lateinit var binding: AthletesDialogBinding
    private lateinit var validator: Validator

    @NotEmpty(message = "Athlete's Field is required")
    lateinit var nameEditText: TextInputEditText

    @NotEmpty(message = "Main Competing Sport Field is required")
    lateinit var competingSportEditText: TextInputEditText

    @NotEmpty(message = "Country Of Origin Field is required")
    lateinit var countryOfOriginEditText: TextInputEditText

    @NotEmpty(message = "Personal Best Field is required")
    lateinit var personalBestEditText: TextInputEditText

    @NotEmpty(message = "Number of Medals Field is required")
    lateinit var numberOfMedalsEditText: TextInputEditText

    @NotEmpty(message = "Interesting Facts Field is required")
    lateinit var interestingFactsEditText: TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AthletesDialogBinding.inflate(layoutInflater)
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

        binding.addAthletesTextView.setOnClickListener {
            validator.validate()
        }

        binding.cancelTextView.setOnClickListener {
            dismiss()
        }
    }

    private fun initializingFields() {
        nameEditText = binding.athletesEditText
        competingSportEditText = binding.competingSportEditText
        countryOfOriginEditText = binding.countryOfOriginEdit
        personalBestEditText = binding.personalBestEdit
        numberOfMedalsEditText = binding.noMedialEdit
        interestingFactsEditText = binding.interestingFactEdit

    }

    override fun onValidationSucceeded() {
        val name = nameEditText.text.toString()
        val competingSport = competingSportEditText.text.toString()
        val origin = countryOfOriginEditText.text.toString()
        val personalBest = personalBestEditText.text.toString()
        val noMedal = numberOfMedalsEditText.text.toString()
        val facts = interestingFactsEditText.text.toString()

        itemClicked.onAthletesItemClick(AthletesModel(
            name,competingSport,origin,personalBest,noMedal,facts
        ), dialog)
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
       activity?.onErrorTextFieldValidator(errors)
    }
}

interface OnAddAthletesItemClicked{
    fun onAthletesItemClick(athletesModel: AthletesModel, dialog: Dialog?)
}