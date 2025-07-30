/**
 * Author: Jose Caicedo (transcribed, original code by Dr. Clinkenbeard, PhD)
 * Date: 7/28/2025
 * <p>
 * Explanation: This adapter is used for displaying GymLog entries in a
 * RecyclerView. It leverages DiffUtil for efficient list updates and uses
 * GymLogViewHolder to display each entry. I didn’t create the logic (it was
 * provided by Dr. Clinkenbeard) but added these comments to make the purpose
 * and functionality clearer for future reference.
 */

package com.example.gymlog.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.gymlog.database.entities.GymLog;

/**
 * Adapter class for binding a list of GymLog entries to a RecyclerView.
 * It uses ListAdapter for automatic diffing and efficient updates.
 */
public class GymLogAdapter extends ListAdapter<GymLog, GymLogViewHolder> {

    /**
     * Creates a new GymLogAdapter instance.
     *
     * @param diffCallback The DiffUtil callback for comparing GymLog items.
     */
    public GymLogAdapter(@NonNull DiffUtil.ItemCallback<GymLog> diffCallback) {
        super(diffCallback);
    }

    /**
     * Called when a new ViewHolder needs to be created.
     *
     * @param parent The parent ViewGroup.
     * @param viewType The type of view (not used here, only one type).
     * @return A new GymLogViewHolder.
     */
    @Override
    public GymLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GymLogViewHolder.create(parent);
    }

    /**
     * Binds data from a GymLog object to the provided ViewHolder.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position in the adapter's data list.
     */
    @Override
    public void onBindViewHolder(@NonNull GymLogViewHolder holder, int position) {
        GymLog current = getItem(position);
        holder.bind(current.toString());
    }

    /**
     * DiffUtil callback for efficiently checking if GymLog items or their contents
     * have changed, so the RecyclerView only redraws the necessary elements.
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
         *
         * @param oldItem The old GymLog entry.
         * @param newItem The new GymLog entry.
         * @return true if the contents match, false otherwise.
         */
        @Override
        public boolean areContentsTheSame(@NonNull GymLog oldItem, @NonNull GymLog newItem) {
            return oldItem.equals(newItem);
        }
    }
}
