package com.example.marvelappx.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelappx.R
import com.github.barteksc.pdfviewer.PDFView

class PdfActivity : AppCompatActivity() {
    //ProgressBar progressBar;
    var pdfView: PDFView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdf_view)
        pdfView = findViewById(R.id.pdf_View)
        pdfView.fromAsset("alien.pdf").load()
    }
}