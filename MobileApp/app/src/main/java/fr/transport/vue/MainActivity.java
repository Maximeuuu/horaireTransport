package fr.transport.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import fr.transport.Controleur;
import fr.transport.mobileapp.R;

import fr.transport.modele.FileToLigneTransport;
import fr.transport.modele.entite.LigneTransport;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Controleur ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ctrl = new Controleur();

        Button btnOuvrir = findViewById(R.id.button_open_file);
        btnOuvrir.setOnClickListener( this );
    }

    public void ouvrirFiltreActivity()
    {
        Intent intent = new Intent(this, FiltreActivity.class );
        intent.putExtra(Controleur.class.getName(), this.ctrl );
        startActivity( intent );
    }

    @Override
    public void onClick( View v )
    {
        Spinner lstFichiers = findViewById(R.id.spinner_files);
        String itemFichier = (String)lstFichiers.getSelectedItem();

        this.ctrl.setLigneTransport( this, itemFichier );

        this.ouvrirFiltreActivity();
    }
}