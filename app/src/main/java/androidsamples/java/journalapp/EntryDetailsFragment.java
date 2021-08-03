package androidsamples.java.journalapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class EntryDetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "EntryDetailsFragment";
    private EditText mEditTitle;
    private String Date, Start, End;
    private Button mEditDate, mEditStart, mEditEnd;
    private EntryDetailsViewModel mEntryDetailsViewModel;
    private JournalEntry mEntry;
    private static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final String[] shareChoices = {"Text", "Email"};
    private String result;
    private boolean fullEntry;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        result = "";
        fullEntry = false;

        mEntryDetailsViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsViewModel.class);

        UUID entryId = (UUID) getArguments().getSerializable(MainActivity.KEY_ENTRY_ID);
        Log.d(TAG, "Loading entry: " + entryId);

        mEntryDetailsViewModel.loadEntry(entryId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.journal_entry_detail, container, false);
        mEditTitle = view.findViewById(R.id.edit_title);
        mEditDate = view.findViewById(R.id.btn_date);
        mEditStart = view.findViewById(R.id.btn_start);
        mEditEnd = view.findViewById(R.id.btn_end);

         view.findViewById(R.id.btn_start).setOnClickListener(this::showStartDialog);
         view.findViewById(R.id.btn_end).setOnClickListener(this::showEndDialog);
         view.findViewById(R.id.btn_date).setOnClickListener(this::showDatePickerDialog);
         view.findViewById(R.id.btn_save).setOnClickListener(this::saveEntry);

        return view;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.journal_entry_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_delete) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setTitle("Delete this Entry?");
            alertDialogBuilder.setMessage("Are you sure you want to delete this entry?");

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    startActivity(new Intent(getContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    mEntryDetailsViewModel.deleteEntry(mEntry);
                }
            });
            alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return true;
        }
        // If the user hits the share button
        if (item.getItemId() == R.id.menu_share) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            // case for if the user filled out all entry information
            if (fullEntry){
                // Title was not fitting, so create custom title
                TextView dialogTitle = new TextView(getContext());
                dialogTitle.setText("Look what I have been up to: " + mEntry.title() + " on " + mEntry.getDate() + " from " +
                        mEntry.getStart() + " to " + mEntry.getEnd() + "\n \n" + " How would you like to share this entry?");
                dialogTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                dialogTitle.setTextColor(getResources().getColor(R.color.black));
                alertDialogBuilder.setCustomTitle(dialogTitle);

                alertDialogBuilder.setSingleChoiceItems(shareChoices,-1, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        result = shareChoices[i];
                    }
                });

                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
            }
            else {
                alertDialogBuilder.setTitle("Incomplete Entry");
                alertDialogBuilder.setMessage("All information must be filled out completely to share this entry");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
            }
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // lets the user pick their desired date
    private void showDatePickerDialog(View v){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    // reformats the date specified by the user to include day of week and month name
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
        GregorianCalendar cal = new GregorianCalendar(year + 1900, month, dayOfMonth);
        String dayOfWeek = simpledateformat.format(cal.getTime());
        String date = dayOfWeek + ", " + MONTHS[month] + " " + dayOfMonth + ", " + year;
        mEditDate.setText(date);
    }
    // allows the user to pick a start-time
    private void showStartDialog(View v){
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(0, 0, 0, hour, minute);
                String time = DateFormat.format("hh:mm aa", calendar).toString();
                Start = time;
                mEditStart.setText(Start);
            }
        },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE), true
        );
        timePickerDialog.show();
    }
    // allows the user to pick an end-time
    private void showEndDialog(View v){
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(0, 0, 0,hour, minute);
                End = DateFormat.format("hh:mm aa", calendar).toString();
                mEditEnd.setText(End);
            }
        },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE), true

        );
        timePickerDialog.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEntryDetailsViewModel.getEntryLiveData().observe(getActivity(),
                entry -> {
                    this.mEntry = entry;
                    if (entry == null){
                    }
                    else {
                        updateUI();
                    }
                });
    }

    private void updateUI() {
        mEditTitle.setText(mEntry.title());
        mEditDate.setText(mEntry.getDate());
        mEditStart.setText(mEntry.getStart());
        mEditEnd.setText(mEntry.getEnd());
        // checks to see if everything has been filled in, if not then the user cannot share
        if (mEntry.title().length() > 0 && mEntry.getDate().length() > 0 && mEntry.getStart().length() > 0 && mEntry.getEnd().length() > 0){
            fullEntry = true;
        }
    }

    private void saveEntry(View v) {
        Log.d(TAG, "Save button clicked");
        mEntry.setTitle(mEditTitle.getText().toString());
        mEntry.setDate(mEditDate.getText().toString());
        mEntry.setStart(mEditStart.getText().toString());
        mEntry.setEnd(mEditEnd.getText().toString());
        mEntryDetailsViewModel.saveEntry(mEntry);
        getActivity().onBackPressed();
    }
}
