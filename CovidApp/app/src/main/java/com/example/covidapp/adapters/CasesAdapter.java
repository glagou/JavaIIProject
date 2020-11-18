package com.example.covidapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.firebase.FirebaseFunctions;
import com.example.covidapp.fragments.CasesFragment;
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
    public void onBindViewHolder(@NonNull CasesViewHolder holder, final int position) {
        final ModelCase modelCase = cases.get(position);
        TextView nameTextView = holder.nameText;
        TextView idTextView = holder.idText;
        ImageButton deleteButton = holder.deleteButton;

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(modelCase.getId());
                CasesFragment.removeFromCases(modelCase);
                cases.remove(position);
                notifyItemRemoved(position);
            }
        });

        idTextView.setText( modelCase.getId());
        nameTextView.setText( modelCase.getFirstName() + " " + modelCase.getLastName());
    }

    public void modifyList(List<ModelCase> newList) {
        cases = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void showDeleteDialog(final String victimId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure that you want to delete this case?");
        builder.setMessage("Deleting this case will remove anything related to it in the database.");

        String positiveText = "OKAY";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseFunctions.deleteVictimFromFirestore(victimId);
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
