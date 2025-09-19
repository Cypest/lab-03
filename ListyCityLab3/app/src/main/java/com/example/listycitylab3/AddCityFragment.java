package com.example.listycitylab3;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    public interface AddCityDialogListener {
        void editCity(int position, City city);
    }

    private static final String ARG_POS = "pos";
    private static final String ARG_CITY = "city";
    private static final String ARG_PROV = "prov";

    public static AddCityFragment newInstance(int position, @Nullable City city) {
        AddCityFragment f = new AddCityFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POS, position);
        if (city != null) {
            b.putString(ARG_CITY, city.getName());
            b.putString(ARG_PROV, city.getProvince());
        }
        f.setArguments(b);
        return f;
    }
    private AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + "must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(requireContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        Bundle args = getArguments();
        int position;
        String existingName = null, existingProv = null;
        if (args != null) {
            position = args.getInt(ARG_POS, -1);
            existingName = args.getString(ARG_CITY, null);
            existingProv = args.getString(ARG_PROV, null);
        } else {
            position = -1;
        }
        boolean isEdit = position >= 0;
        if (isEdit) {
            if (existingName != null) editCityName.setText(existingName);
            if (existingProv != null) editProvinceName.setText(existingProv);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        return builder
                .setView(view)
                .setTitle(isEdit ? "Edit city" : "Add a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(isEdit ? "Save" : "Add", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    listener.editCity(position, new City(cityName, provinceName));
                })
                .create();
    }
}