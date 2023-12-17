package com.example.foodapp.Fragments

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.foodapp.databinding.FragmentContactBinding

class Contact : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val REQUEST_PHONE_CALL = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.callButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(CALL_PHONE), REQUEST_PHONE_CALL)
            } else {
                val phoneIntent = Intent(Intent.ACTION_CALL)
                phoneIntent.data = Uri.parse("tel:1234567890") // Replace with the actual phone number
                startActivity(phoneIntent)
            }
        }
        binding.emailButton.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("chef@foodApp.com")) // recipient's email
                putExtra(Intent.EXTRA_SUBJECT, "Message to Chef") // Email subject
            }

            if (emailIntent.resolveActivity(requireActivity().packageManager) == null) {
                Toast.makeText(requireContext(), "No email app found", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(emailIntent)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PHONE_CALL && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val phoneIntent = Intent(Intent.ACTION_CALL)
            phoneIntent.data = Uri.parse("tel:1234567890") // Replace with the actual phone number
            startActivity(phoneIntent)
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
