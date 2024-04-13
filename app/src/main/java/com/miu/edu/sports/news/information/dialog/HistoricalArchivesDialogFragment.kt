package com.miu.edu.sports.news.information.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.databinding.HistoricalArchivesDialogBinding
import com.miu.edu.sports.news.information.model.HistoricalArchivesModel
import com.miu.edu.sports.news.information.utils.datePickerDialogSetListener
import com.miu.edu.sports.news.information.utils.onErrorTextFieldValidator
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HistoricalArchivesDialogFragment(private val itemClicked: OnAddHistoricalItemClicked) : AppCompatDialogFragment(), Validator.ValidationListener {
    private lateinit var binding: HistoricalArchivesDialogBinding
    private lateinit var validator: Validator

    @NotEmpty(message = "Historical Name Field is required")
    lateinit var historicalNameEditTextView: TextInputEditText

    @NotEmpty(message = "Select Date Field is required")
    lateinit var selectDateEditText: TextInputEditText

    @NotEmpty(message = "Description Field is required")
    lateinit var descriptionEditText: TextInputEditText
    private var calendar = Calendar.getInstance()
    private val formatDate = "MMMM dd, yyyy"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoricalArchivesDialogBinding.inflate(layoutInflater)
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

        /*
       * Initializing validator
       * */
        validator = Validator(this)
        validator.setValidationListener(this)

        binding.addTextView.setOnClickListener {
            validator.validate()
        }

        binding.selectDateEditText.setOnClickListener {

            val datePickerDate = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val simpleDateFormat = SimpleDateFormat(formatDate, Locale.US)
                    val date = simpleDateFormat.format(calendar.time)
                    binding.selectDateEditText.setText(date)
                }

            activity?.datePickerDialogSetListener(Calendar.getInstance(), datePickerDate)
        }

        binding.cancelTextView.setOnClickListener {
            dismiss()
        }
    }

    private fun initializingFields() {
        historicalNameEditTextView = binding.historicalNameEdit
        selectDateEditText = binding.selectDateEditText
        descriptionEditText = binding.descriptionInputEditText
    }

    override fun onValidationSucceeded() {
        val historicalName = binding.historicalNameEdit.text.toString()
        val date = binding.selectDateEditText.text.toString()
        val description = binding.descriptionInputEditText.text.toString()
        itemClicked.onHistoricalAddedClicked(HistoricalArchivesModel(historicalName,description,date), dialog)
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        requireActivity().onErrorTextFieldValidator(errors)
    }
}

interface OnAddHistoricalItemClicked{
    fun onHistoricalAddedClicked(historicalArchivesModel: HistoricalArchivesModel, dialog: Dialog?)
}