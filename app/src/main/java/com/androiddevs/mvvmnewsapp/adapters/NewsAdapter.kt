package com.androiddevs.mvvmnewsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.models.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    /* - DiffUtil.ItemCallBack<>() compares two lists and only updates items that are different
         this will work in the background instead of the main thread
       - this will be an anonymous class */
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            // compare the unique values of the fields (usually its the id)
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    // this will compare the lists using the anonymous object above
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // get the article from the var differ
        val article = differ.currentList[position]

        holder.itemView.apply {
            //Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.Name
            tvTitle.text = article.title
            //tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    /* - passes an Articles as the parameter when an item is clicked
       - this manages Article item clicks separately from the function below  */
    private var onItemClickListener: ((Article) -> Unit)? = null

    //
    fun setOnItemItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        // since the list is managed by the var differ, get the count from there
        return differ.currentList.size
    }
}