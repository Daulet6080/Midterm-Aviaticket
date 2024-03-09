import androidx.recyclerview.widget.DiffUtil
import com.example.aviatickets.model.entity.Offer

class OfferDiffCallback(
    private val oldList: List<Offer>,
    private val newList: List<Offer>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Return true if the two items represent the same object.
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Check whether the content of the items is the same.
        // This method is called only if areItemsTheSame() returns true.
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
