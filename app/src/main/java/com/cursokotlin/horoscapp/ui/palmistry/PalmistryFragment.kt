package com.cursokotlin.horoscapp.ui.palmistry

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
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
            starCamera()
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
            starCamera()
        }else{
            //Lanzate con el permiso de la camara
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }

    }


    //Funcion para usar la camara
    private fun starCamera(){
        //es igual a un proceso de camara provider que requiere el contexto GESTOR DE CAMARA, nos permite que el ciclo de vida se enganche
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            //El get esta creando ese futuro provider, se engancha al ciclo de vida
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    //Y ademas accede a la vista viewFinder
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            //Seleccionamos la camara que queremos por defecto
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                //Que se desvindee de lo que tenia antes
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(this, cameraSelector, preview)

            }catch (e: Exception){
                Log.e("Ari", "Algo se rompio ${e.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))

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