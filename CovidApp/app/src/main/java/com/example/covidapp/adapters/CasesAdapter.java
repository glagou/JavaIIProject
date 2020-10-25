package com.example.covidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.models.ModelCase;

import java.util.List;

public class CasesAdapter extends RecyclerView.Adapter<CasesAdapter.CasesViewHolder> {

    private Context context;
    private List<ModelCase> cases;

    public CasesAdapter(Context context, List<ModelCase> cases) {
        this.context = context;
        this.cases = cases;
    }

    @NonNull
    @Override
    public CasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_case, parent, false);
        return new CasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CasesViewHolder holder, int position) {
        ModelCase modelCase = cases.get(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CasesViewHolder extends RecyclerView.ViewHolder {

        TextView idText, nameText, phoneText, residenceText, dateOfDiseaseText,
        genderText, closeContactWithText, phonesOfCloseContactsText, ageText, susceptibleText;

        public CasesViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.id_text);
            nameText = itemView.findViewById(R.id.name_text);
            phoneText = itemView.findViewById(R.id.phone_text);
            residenceText = itemView.findViewById(R.id.residence_text);
            dateOfDiseaseText = itemView.findViewById(R.id.date_of_disease_text);
            genderText = itemView.findViewById(R.id.gender_text);
            closeContactWithText = itemView.findViewById(R.id.close_contact_with_text);
            phonesOfCloseContactsText = itemView.findViewById(R.id.phones_of_close_contacts_text);
            ageText = itemView.findViewById(R.id.age_text);
            susceptibleText = itemView.findViewById(R.id.susceptible_text);
        }
    }
}
