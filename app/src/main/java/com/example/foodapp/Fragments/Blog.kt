package com.example.foodapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.Adaptar.BlogAdapter
import com.example.foodapp.DataClass.BlogDataClass
import com.example.foodapp.Interfaces.FabActionHandlerBlogs
import com.example.foodapp.databinding.FragmentBlogBinding
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class Blog : Fragment(), FabActionHandlerBlogs{

    private var _binding: FragmentBlogBinding? = null
    private val binding get() = _binding!!

    private val blogList = ArrayList<BlogDataClass>()
    private lateinit var blogAdapter: BlogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBlogBinding.inflate(inflater, container, false);
        initializeBlogList()
        blogAdapter = BlogAdapter(blogList)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = blogAdapter
        }
        binding.refreshButton.setOnClickListener {
            refreshBlogList()
        }
        return binding.root;
    }
    private fun refreshBlogList() {
        // Clear the existing data
        blogList.clear()

        // Reinitialize the data
        initializeBlogList()

        // Notify the adapter of the data change
        blogAdapter.notifyDataSetChanged()
    }
    private fun  initializeBlogList() {
        val dummyData = arrayOf(
            BlogDataClass(
                userId = 1,
                id = 1,
                title = "First Blog Post",
                body = "This is the first blog post. It's a sunny day, and I decided to go for a long hike in the mountains. The trail was challenging, but the breathtaking views made it worth it. I reached the summit and had a picnic lunch while enjoying the serene beauty of nature. After descending, I shared my hiking experiences with friends over a bonfire in the evening."
            ),
            BlogDataClass(
                userId = 2,
                id = 2,
                title = "Second Blog Post",
                body = "In this blog post, I want to share my passion for cooking. Cooking is not just a hobby for me; it's a creative outlet. Today, I prepared a gourmet meal from scratch. I marinated the chicken in a special blend of herbs and spices, and the aroma filled the kitchen. The result was a delicious, restaurant-quality dish that I enjoyed with a glass of fine wine."
            ),
            BlogDataClass(
                userId = 3,
                id = 3,
                title = "Third Blog Post",
                body = "Welcome to my blog! In this post, I'll discuss the importance of fitness in our daily lives. Regular exercise not only keeps us physically fit but also boosts our mental well-being. I started my day with a brisk morning run, followed by a refreshing yoga session. These healthy habits have positively impacted my life, and I encourage you to prioritize your fitness journey too."
            ),
            BlogDataClass(
                userId = 4,
                id = 4,
                title = "Fourth Blog Post",
                body = "Today, I want to share my thoughts on the latest tech trends. The world of technology is ever-evolving, and staying updated is crucial for a software engineer like me. From AI and machine learning to blockchain and IoT, there's so much happening. Embracing these innovations can lead to exciting opportunities in the tech industry."
            ),
            BlogDataClass(
                userId = 5,
                id = 5,
                title = "Fifth Blog Post",
                body = "Traveling is a passion that my wife and I share. We've explored numerous countries, experiencing diverse cultures and cuisines. Our recent trip to Southeast Asia was an unforgettable adventure. From the bustling streets of Bangkok to the tranquil beaches of Bali, we created memories that will last a lifetime. Traveling together has strengthened our bond and enriched our lives."
            ),
            BlogDataClass(
                userId = 6,
                id = 6,
                title = "Sixth Blog Post",
                body = "In this blog post, I want to delve into the world of literature. Reading has been a lifelong hobby, and I recently discovered a hidden gem of a book. The author's prose is captivating, and I found myself immersed in a world of imagination. Reading has the power to transport us to different realms, and I believe everyone should explore the magic of books."
            ),
            BlogDataClass(
                userId = 7,
                id = 7,
                title = "Seventh Blog Post",
                body = "Let's talk about the exciting world of gaming. As a software engineer, I appreciate the technical aspects of game development. I recently played a visually stunning game with a gripping storyline. The attention to detail in the graphics and gameplay was impressive. Gaming isn't just a form of entertainment; it's a form of art that continues to evolve."
            ),
            BlogDataClass(
                userId = 8,
                id = 8,
                title = "Eighth Blog Post",
                body = "Today, I want to explore the realm of music. Music has the incredible ability to evoke emotions and memories. I attended a live concert recently, and the energy in the crowd was electrifying. Whether it's classical compositions or modern hits, music connects us on a universal level. It's a language that transcends boundaries."
            ),
            BlogDataClass(
                userId = 9,
                id = 9,
                title = "Ninth Blog Post",
                body = "Let's dive into the world of productivity. Being a software engineer, efficient time management is crucial. I've adopted various productivity hacks that help me stay focused and organized. From setting SMART goals to using time-tracking apps, these strategies have enhanced my productivity and work-life balance."
            ),
            BlogDataClass(
                userId = 10,
                id = 10,
                title = "Tenth Blog Post",
                body = "Learning a new language can be an enriching experience. I recently embarked on the journey of mastering a new language, and it has opened doors to new cultures and perspectives. Language is a tool for communication and understanding, and multilingualism broadens our horizons. I encourage everyone to explore the world of languages."
            ),
            BlogDataClass(
                userId = 11,
                id = 11,
                title = "Eleventh Blog Post",
                body = "In this blog post, I want to share my experiences with home improvement projects. Renovating a living space can be both challenging and rewarding. From painting walls to installing new fixtures, each project adds a personal touch to our home. I recently revamped my kitchen, and the transformation was remarkable. Home improvement allows us to create a comfortable and aesthetically pleasing environment."
            ),
            BlogDataClass(
                userId = 12,
                id = 12,
                title = "Twelfth Blog Post",
                body = "I'm passionate about gourmet food, and I believe that cooking is an art. My recent culinary adventure involved preparing a five-course meal for friends and family. From appetizers to desserts, each dish was carefully crafted. The combination of flavors and presentation created a memorable dining experience. Cooking connects people and fosters a sense of togetherness."
            ),
            BlogDataClass(
                userId = 13,
                id = 13,
                title = "Thirteenth Blog Post",
                body = "Environmental conservation is a topic close to my heart. I recently participated in a tree planting initiative in my community. Planting trees not only enhances the natural beauty of the area but also contributes to a healthier planet. It's essential for each of us to take small steps towards preserving the environment for future generations."
            ),
            BlogDataClass(
                userId = 14,
                id = 14,
                title = "Fourteenth Blog Post",
                body = "Pets bring immense joy to our lives, and I want to share my insights into pet care. I have a lovely dog named Max, and taking care of him is a delightful responsibility. From regular walks to nutritious meals, pet care is an expression of love and companionship. Max has become an integral part of our family."
            ),
            BlogDataClass(
                userId = 15,
                id = 15,
                title = "Fifteenth Blog Post",
                body = "Art and creativity are essential for personal expression. I recently explored the world of painting and discovered the therapeutic benefits of art. Whether it's with a canvas and brushes or digital art, the act of creating allows us to channel our emotions and thoughts. Artistic endeavors are a form of self-discovery and a source of inspiration."
            ),
            BlogDataClass(
                userId = 16,
                id = 16,
                title = "Sixteenth Blog Post",
                body = "Cultural exploration is a significant aspect of my travels. Experiencing different cultures broadens our perspectives and fosters mutual understanding. I had the privilege of attending a traditional festival in Japan, where I immersed myself in the customs and traditions of the country. Cultural exchanges remind us of the beauty of diversity."
            ),
            BlogDataClass(
                userId = 17,
                id = 17,
                title = "Seventeenth Blog Post",
                body = "DIY projects are not only a hobby but also a way to enhance our skills. I recently tackled a home DIY project, building a custom bookshelf. The satisfaction of creating something with your own hands is unparalleled. DIY projects encourage creativity and problem-solving, making them a valuable endeavor."
            ),
            BlogDataClass(
                userId = 18,
                id = 18,
                title = "Eighteenth Blog Post",
                body = "Financial planning is crucial for a secure future. I've been actively managing my finances and investing wisely. From setting a budget to diversifying investments, financial planning provides peace of mind and the freedom to pursue our dreams. It's never too early to start planning for a financially stable retirement."
            ),
            BlogDataClass(
                userId = 19,
                id = 19,
                title = "Nineteenth Blog Post",
                body = "Fashion is a form of self-expression that extends beyond clothing. I've always been fascinated by fashion trends and how they reflect society's values. In this blog post, I'll explore the influence of fashion on culture and identity. Fashion is a dynamic art form that evolves with time."
            ),
            BlogDataClass(
                userId = 20,
                id = 20,
                title = "Twentieth Blog Post",
                body = "Science and technology are at the forefront of innovation. As a software engineer, I'm passionate about the potential of technology to shape the future. In this post, I'll discuss the latest advancements in AI and how they impact various industries. The convergence of science and technology opens up exciting possibilities."
            )
        )


        val maxStartIndex = dummyData.size - 5
        val startIndex = Random.nextInt(0, max(maxStartIndex, 1))

        val endIndex = min(startIndex + 5, dummyData.size)

        blogList.addAll(dummyData.slice(startIndex until endIndex))
    }

    override fun onFabClicked(blogs: BlogDataClass) {
        blogList.add(blogs)
    }


}

