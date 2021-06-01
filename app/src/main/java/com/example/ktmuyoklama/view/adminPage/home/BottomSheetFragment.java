package com.example.ktmuyoklama.view.adminPage.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.view.ListLessonPage;
import com.example.ktmuyoklama.view.ListStudentPage;
import com.example.ktmuyoklama.view.ListTeacherPage;
import com.example.ktmuyoklama.view.adminPage.search.SearchLessonFragment;
import com.example.ktmuyoklama.view.adminPage.search.SearchStudentFragment;
import com.example.ktmuyoklama.view.adminPage.search.SearchTeacherFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment
        implements View.OnClickListener{
    public static final String TAG = "ActionBottomDialog";
    private ItemClickListener mListener;
    View view;
    Button studentSearch, teacherSearch, lessonSearch;

    
    public static BottomSheetFragment newInstance() {
        return new BottomSheetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_botton_sheet, container, false);

        studentSearch = view.findViewById(R.id.studentSearch);
        studentSearch.setOnClickListener(v -> {
//            FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container_admin, new SearchStudentFragment());
//            ft.commit();
            Intent stSearch = new Intent(getActivity().getApplication(), ListStudentPage.class);
            startActivity(stSearch);
            dismiss();
        });
        teacherSearch = view.findViewById(R.id.teacherSearch);
        teacherSearch.setOnClickListener(v -> {
//            FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container_admin, new SearchTeacherFragment());
//            ft.commit();
            Intent stSearch = new Intent(getActivity().getApplication(), ListTeacherPage.class);
            startActivity(stSearch);

            dismiss();
        });
        lessonSearch = view.findViewById(R.id.lessonSearch);
        lessonSearch.setOnClickListener(v -> {
//            FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container_admin, new SearchLessonFragment());
//            ft.commit();
            Intent stSearch = new Intent(getActivity().getApplication(), ListLessonPage.class);
            startActivity(stSearch);
            dismiss();
        });

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListener) {
            mListener = (ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ItemClickListener");
        }
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