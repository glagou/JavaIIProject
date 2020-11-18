package com.example.covidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.models.ModelCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CasesAdapter extends RecyclerView.Adapter<CasesAdapter.CasesViewHolder> {

    private Context context;
    private List<ModelCase> cases;

    public CasesAdapter(Context context, List<ModelCase> cases) {
        this.context = context;
        this.cases = new ArrayList<>(cases);
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
        TextView nameTextView = holder.nameText;
        TextView idTextView = holder.idText;
        ImageButton deleteButton = holder.deleteButton;
        /*TextView phoneTextView = holder.phoneText;
        TextView residenceTextView = holder.residenceText;
        TextView dateOfDiseaseTextView = holder.dateOfDiseaseText;
        TextView genderTextView = holder.genderText;
        TextView closeContactsWithTextView = holder.closeContactWithText;
        TextView phoneOfCloseContactsTextView = holder.phonesOfCloseContactsText;
        TextView ageTextView = holder.ageText;
        TextView susceptibleTextView = holder.susceptibleText;*/
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, nameTextView.getText(), Toast.LENGTH_SHORT).show();
            }

        idTextView.setText( modelCase.getId());
        nameTextView.setText( modelCase.getFirstName() + " " + modelCase.getLastName());
        /*phoneTextView.setText("Phone: " + modelCase.getPhone());
        residenceTextView.setText("Residence Region: " + modelCase.getResidenceRegion());
        dateOfDiseaseTextView.setText("Date Of Disease: " + modelCase.getDateOfDisease());
        genderTextView.setText("Gender: " + modelCase.getGender());
        ageTextView.setText("Age: " + modelCase.getAge());
        if(modelCase.isSusceptible()) {
            susceptibleTextView.setText("Susceptible: Yes");
        } else {
            susceptibleTextView.setText("Susceptible: No");
        }

        for(int i = 0; i < modelCase.getCloseContactWith().length; i ++)
        {
            if(i == 0) {
                closeContactsWithTextView.setText("Close Contact With: " + modelCase.getCloseContactWith()[0]);
                phoneOfCloseContactsTextView.setText("Close Contact With Phones: " + modelCase.getPhonesOfCloseContact()[0]);
            } else {
                closeContactsWithTextView.setText(closeContactsWithTextView.getText() + ", " + modelCase.getCloseContactWith()[i]);
                phoneOfCloseContactsTextView.setText(phoneOfCloseContactsTextView.getText() + ", " + modelCase.getPhonesOfCloseContact());
            }
        }*/

    }

    public void modifyList(List<ModelCase> newList) {
        cases = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return cases.size();
    }

    public class CasesViewHolder extends RecyclerView.ViewHolder {

        TextView idText, nameText;
        ImageButton deleteButton;

        public CasesViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.id_text);
            nameText = itemView.findViewById(R.id.name_text);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
