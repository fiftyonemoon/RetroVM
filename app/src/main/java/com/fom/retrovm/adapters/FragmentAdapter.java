package com.fom.retrovm.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fom.retrovm.databinding.AdapterFragmentBinding;
import com.fom.retrovm.models.User;

import java.util.ArrayList;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.MyViewHolder> {

    private ArrayList<User> list = new ArrayList<>();
    private OnItemClickListener listener;

    public FragmentAdapter() {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, User user);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterFragmentBinding binding = AdapterFragmentBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyViewHolder(binding);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public AdapterFragmentBinding binding;

        MyViewHolder(AdapterFragmentBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    User user = list.get(position);
                    listener.onItemClick(position, user);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.binding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}