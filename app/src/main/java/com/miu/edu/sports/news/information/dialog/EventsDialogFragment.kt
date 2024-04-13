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
import com.miu.edu.sports.news.information.databinding.EventsDialogBinding
import com.miu.edu.sports.news.information.model.EventsModel
import com.miu.edu.sports.news.information.utils.datePickerDialogSetListener
import com.miu.edu.sports.news.information.utils.onErrorTextFieldValidator
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EventsDialogFragment(private val itemClicked: OnAddEventsItemClicked) : AppCompatDialogFragment(), Validator.ValidationListener {
    private lateinit var binding: EventsDialogBinding
    private lateinit var validator: Validator

    @NotEmpty(message = "Event Name Field is required")
    lateinit var eventsNameEditTextView: TextInputEditText

    @NotEmpty(message = "Select Date Field is required")
    lateinit var selectDateEditText: TextInputEditText

    @NotEmpty(message = "Event Description Field is required")
    lateinit var descriptionEditText: TextInputEditText
    private var calendar = Calendar.getInstance()
    private val formatDate = "dd/MM/yyyy"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EventsDialogBinding.inflate(layoutInflater)
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

        binding.addEventTextView.setOnClickListener {
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
        eventsNameEditTextView = binding.eventNameEdit
        selectDateEditText = binding.selectDateEditText
        descriptionEditText = binding.eventDescriptionInputEditText
    }

    override fun onValidationSucceeded() {
        val eventName = binding.eventNameEdit.text.toString()
        val date = binding.selectDateEditText.text.toString()
        val description = binding.eventDescriptionInputEditText.text.toString()
        itemClicked.onEventsAdded(EventsModel(eventName,date,description), dialog)
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        requireActivity().onErrorTextFieldValidator(errors)
    }
}

interface OnAddEventsItemClicked{
    fun onEventsAdded(eventsModel: EventsModel, dialog: Dialog?)
}