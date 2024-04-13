package com.miu.edu.sports.news.information.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.databinding.SportsDialogBinding
import com.miu.edu.sports.news.information.model.SportsModel
import com.miu.edu.sports.news.information.utils.onErrorTextFieldValidator
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty

class SportsDialog(private val itemClicked: OnAddNewSportsItemClicked) : AppCompatDialogFragment(), Validator.ValidationListener {
    private lateinit var binding: SportsDialogBinding
    private lateinit var validator: Validator

    @NotEmpty(message = "SportsType Field is required")
    lateinit var sportsType: AutoCompleteTextView

    @NotEmpty(message = "SportsName Field is required")
    lateinit var sportsName: TextInputEditText

    @NotEmpty(message = "Instruction Field is required")
    lateinit var instruction: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SportsDialogBinding.inflate(layoutInflater)
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

        val selectLeaveTypes = arrayOf(getString(R.string.measure),
            getString(R.string.precision), getString(R.string.spectacle),
            getString(R.string.combat), getString(R.string.play))

        val sportsTypeArrayAdapter= ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            selectLeaveTypes
        )
        binding.sportsTypeDropDown.setAdapter(sportsTypeArrayAdapter)

        binding.addNewSportsTextView.setOnClickListener {
            validator.validate()
        }

        binding.cancelTextView.setOnClickListener {
            dismiss()
        }
    }

    private fun initializingFields() {
        sportsType = binding.sportsTypeDropDown
        sportsName = binding.sportsInputEditText
        instruction = binding.instructionInputEditText
    }

    override fun onValidationSucceeded() {
        val sportsType = binding.sportsTypeDropDown.text.toString()
        val sportsName = binding.sportsInputEditText.text.toString()
        val instruction = binding.instructionInputEditText.text.toString()
        itemClicked.onNewSportsAdded(SportsModel(sportsName,sportsType,instruction), dialog)
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        requireActivity().onErrorTextFieldValidator(errors)
    }
}

interface OnAddNewSportsItemClicked{
    fun onNewSportsAdded(sportsModel: SportsModel, dialog: Dialog?)
}