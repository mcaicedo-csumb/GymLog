/**
 * Author: Maria Caicedo (transcribed, original code by Dr. Clinkenbeard, PhD)
 * Date: 7/29/2025
 * <p>
 */

package com.example.gymlog.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.gymlog.database.entities.GymLog;

public class GymLogAdapter extends ListAdapter<GymLog, GymLogViewHolder> {

    /**
     * @param diffCallback The DiffUtil callback for comparing GymLog items.
     */
    public GymLogAdapter(@NonNull DiffUtil.ItemCallback<GymLog> diffCallback) {
        super(diffCallback);
    }

    /**
     * @param parent The parent ViewGroup.
     * @return A new GymLogViewHolder.
     */
    @Override
    public GymLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GymLogViewHolder.create(parent);
    }

    /**
     * Binds data from a GymLog object to the provided ViewHolder.
     */
    @Override
    public void onBindViewHolder(@NonNull GymLogViewHolder holder, int position) {
        GymLog current = getItem(position);
        holder.bind(current.toString());
    }

    /**
     * DiffUtil callback for efficiently checking if GymLog items or their contents
     */
    public static class GymLogDiff extends DiffUtil.ItemCallback<GymLog> {

        /**
         * Checks if two GymLog objects are the same (by reference).
         *
         * @param oldItem The old GymLog entry.
         * @param newItem The new GymLog entry.
         * @return true if they are the same object, false otherwise.
         */
        @Override
        public boolean areItemsTheSame(@NonNull GymLog oldItem, @NonNull GymLog newItem) {
            return oldItem == newItem;
        }

        /**
         * Checks if the content of two GymLog objects is identical.
         */
        @Override
        public boolean areContentsTheSame(@NonNull GymLog oldItem, @NonNull GymLog newItem) {
            return oldItem.equals(newItem);
        }
    }
}
