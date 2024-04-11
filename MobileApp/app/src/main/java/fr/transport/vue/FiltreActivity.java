package fr.transport.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import fr.transport.Controleur;
import fr.transport.mobileapp.R;
import fr.transport.modele.entite.Jour;
import fr.transport.modele.entite.Lieu;
import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.Periode;
import fr.transport.modele.entite.Trajet;

public class FiltreActivity extends AppCompatActivity implements View.OnClickListener
{
	private Controleur ctrl;

	private Spinner lstDepart;
	private Spinner lstArrivee;
	private Spinner lstPeriode;
	private Spinner lstJour;
	private Button btnValider;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filtre);

		Intent intent = getIntent();
		this.ctrl = (Controleur) intent.getSerializableExtra("controleur");

		//Log.d( "LectureFichierCSV", this.ctrl.getLigneTransport().toString() );

		this.lstDepart = findViewById( R.id.spinnerDepart );
		this.lstArrivee = findViewById( R.id.spinnerArrivee );
		this.lstPeriode = findViewById( R.id.spinnerPeriode );
		this.lstJour = findViewById( R.id.spinnerJour );
		this.btnValider = findViewById( R.id.buttonValider );

		remplirListe( this.ctrl.getEnsArret(), this.lstDepart );
		remplirListe( this.ctrl.getEnsArret(), this.lstArrivee );
		remplirListe( this.ctrl.getEnsPeriode(), this.lstPeriode );
		remplirListe( this.ctrl.getEnsJour(), this.lstJour );

		this.btnValider.setOnClickListener( this );
	}

	private void remplirListe(List<String> ens, Spinner liste )
	{
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ens );
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		liste.setAdapter( adapter );
	}

	public void ouvrirTrajetsActivity()
	{
		Intent intent = new Intent(this, TrajetsActivity.class );
		intent.putExtra("controleur", this.ctrl );
		startActivity( intent );
	}

	public void onClick( View v )
	{
		String depart = (String)this.lstDepart.getSelectedItem();
		this.ctrl.lieuDepart = new Lieu( depart );

		String arrivee = (String)this.lstArrivee.getSelectedItem();
		this.ctrl.lieuArrivee = new Lieu( arrivee );

		String nomPeriode = (String)this.lstPeriode.getSelectedItem();
		Periode periode = null;
		for( Periode p : Periode.values() )
		{
			if( p.getNom().equals( nomPeriode ) )
			{
				periode = p;
			}
		}

		String nomJour = (String)this.lstJour.getSelectedItem();
		Jour jour = null;
		for( Jour j : Jour.values() )
		{
			if( j.getNom().equals( nomJour ) )
			{
				jour = j;
			}
		}

		this.ctrl.filtrer( this.ctrl.lieuDepart, this.ctrl.lieuArrivee, periode, jour );
		//Log.d( "filtres", depart + " - " + arrivee + " - " + periode + " - " + jour );

		this.ouvrirTrajetsActivity();
	}
}