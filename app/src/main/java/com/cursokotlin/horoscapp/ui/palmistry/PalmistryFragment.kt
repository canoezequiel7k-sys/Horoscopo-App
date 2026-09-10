package com.cursokotlin.horoscapp.ui.palmistry

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import com.cursokotlin.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    companion object{
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }



    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted ->
        if (isGranted){
            //Lo acepta
            //starCamera
        }else{
            //No lo acepta
            Toast.makeText(requireContext(), "Acepta los permisos para poder disfrutar una experiencia magica", Toast.LENGTH_LONG).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //comprobar que tiene permise
        if (checkCameraPermission()){
            //Tiene permisos aceptados
            //starCamera
        }else{
            //Lanzate con el permiso de la camara
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }

    }

    //Funcion que verifique si tiene permiso, esto va a devolver un Boolean
    private fun checkCameraPermission(): Boolean {
        //comprueba este permiso, con el contexto, comprueba que este permiso del manifest este aceptado
        return PermissionChecker.checkSelfPermission(
            requireContext(),
            CAMERA_PERMISSION
        ) == PermissionChecker.PERMISSION_GRANTED
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}