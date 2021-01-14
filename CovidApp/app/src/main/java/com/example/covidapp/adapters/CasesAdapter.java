package com.example.covidapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.activities.ViewContactActivity;
import com.example.covidapp.firebase.FirebaseFunctions;
import com.example.covidapp.fragments.CasesFragment;
import com.example.covidapp.models.ModelCase;

import java.util.ArrayList;
import java.util.List;

public class CasesAdapter extends RecyclerView.Adapter<CasesAdapter.CasesViewHolder> {

    private Context context;
    private List<ModelCase> cases;
    private Activity activity;

    public CasesAdapter(Context context, List<ModelCase> cases, Activity activity) {
        this.context = context;
        this.cases = new ArrayList<>(cases);
        this.activity = activity;
    }

    @NonNull
    @Override
    public CasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_case, parent, false);
        return new CasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CasesViewHolder holder, final int position) {
        final ModelCase modelCase = cases.get(position);
        TextView nameTextView = holder.nameText;
        TextView idTextView = holder.idText;
        ImageButton deleteButton = holder.deleteButton;
        CardView card_view = holder.card_view;

        final String fullName = modelCase.getFirstName() + " " + modelCase.getLastName();
        final String id = modelCase.getId();
        final String phone = modelCase.getPhone();
        final String residence = modelCase.getResidenceRegion();
        final String date = modelCase.getDateOfDisease();
        final String gender = modelCase.getGender();
        final String age = String.valueOf(modelCase.getAge());
        final String isSusceptible = (modelCase.isSusceptible() ? "True" : "False");
        final String[] closeContactWith = modelCase.getCloseContactWith();
        final String[] phonesCloseContactWith = modelCase.getPhonesOfCloseContact();
        String finalCloseContactWith = "";

        if(closeContactWith != null) {
            for(int i = 0; i < closeContactWith.length; i++) {
                finalCloseContactWith = finalCloseContactWith + closeContactWith[i] + "," + phonesCloseContactWith[i] + "\n";
            }
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(modelCase.getId(), modelCase, position);
            }
        });

        idTextView.setText(id);
        nameTextView.setText(fullName);

        final String finalCloseContactWith1 = finalCloseContactWith;
        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadViewCaseActivity(fullName, id, phone, residence,date, gender,
                        age ,isSusceptible, finalCloseContactWith1);
            }
        });
    }

    public void modifyList(List<ModelCase> newList) {
        cases = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void showDeleteDialog(final String victimId, final ModelCase modelCase, final  int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure that you want to delete this case?");
        builder.setMessage("Deleting this case will remove anything related to it in the database.");

        String positiveText = "OKAY";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseFunctions.deleteVictimFromFirestore(victimId);
                        CasesFragment.removeFromCases(modelCase);
                        cases.remove(position);
                        notifyItemRemoved(position);
                    }
                });

        String negativeText = "CANCEL";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return cases.size();
    }

    private void loadViewCaseActivity(String fullName, String id, String phone,
                                      String residence, String date, String gender,
                                      String age, String isSusceptible, String closeContactWith) {
        ViewContactActivity.setFullName(fullName);
        ViewContactActivity.setId(id);
        ViewContactActivity.setPhone(phone);
        ViewContactActivity.setResidenceRegion(residence);
        ViewContactActivity.setDateOfDisease(date);
        ViewContactActivity.setGender(gender);
        ViewContactActivity.setAge(age);
        ViewContactActivity.setSusceptible(isSusceptible);
        ViewContactActivity.setCloseContactWith(closeContactWith);
        Intent intent = new Intent(activity, ViewContactActivity.class);
        context.startActivity(intent);
    }

    public class CasesViewHolder extends RecyclerView.ViewHolder {

        TextView idText, nameText;
        ImageButton deleteButton;
        CardView card_view;

        public CasesViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.id_text);
            nameText = itemView.findViewById(R.id.name_text);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
