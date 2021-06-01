package com.example.ktmuyoklama.view.adminPage.control;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ktmuyoklama.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ControlBottomSheetFragment extends BottomSheetDialogFragment
        implements View.OnClickListener{

    public static final String TAG = "ActionBottomDialog";
    private ItemClickListener mListener;
    View view;
    Button regEdit, ELEdit, LSSEdit;

    public static ControlBottomSheetFragment newInstance() {
        return new ControlBottomSheetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        regEdit = view.findViewById(R.id.regEdit);
        regEdit.setOnClickListener(v -> {
//            FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container_admin, new RegisterEditFragment());
//            ft.commit();
            Intent regEdit = new Intent(getActivity().getApplication(), RegisterEditActivity.class);
            startActivity(regEdit);
            dismiss();
        });
        ELEdit = view.findViewById(R.id.ESEdit);
        ELEdit.setOnClickListener(v -> {
//            FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container_admin, new EkleSilEditFragment());
//            ft.commit();
            Intent esEdit = new Intent(getActivity().getApplication(), EkleSilEditActivity.class);
            startActivity(esEdit);
            dismiss();
        });
        LSSEdit = view.findViewById(R.id.LSSEdit);
        LSSEdit.setOnClickListener(v -> {
//            FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container_admin, new LSSEditFragment());
//            ft.commit();
            Intent LSSEdit = new Intent(getActivity().getApplication(), LSSEditActivity.class);
            startActivity(LSSEdit);
            dismiss();
        });

        return view;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        TextView tvSelected = (TextView) view;
        mListener.onItemClick(tvSelected.getText().toString());
        dismiss();
    }

    public interface ItemClickListener {
        void onItemClick(String item);
    }

}