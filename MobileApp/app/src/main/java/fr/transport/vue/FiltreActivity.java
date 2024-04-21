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
		this.ctrl = (Controleur) intent.getSerializableExtra(Controleur.class.getName());

		this.lstDepart = findViewById( R.id.spinnerDepart );
		this.lstArrivee = findViewById( R.id.spinnerArrivee );
		this.lstPeriode = findViewById( R.id.spinnerPeriode );
		this.lstJour = findViewById( R.id.spinnerJour );
		this.btnValider = findViewById( R.id.buttonValider );

		remplirListe( this.ctrl.getEnsArret(), this.lstDepart );
		remplirListe( this.ctrl.getEnsArret(), this.lstArrivee );
		this.lstArrivee.setSelection( this.lstArrivee.getLastVisiblePosition() ); //TODO: ne marche pas
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
		intent.putExtra(Controleur.class.getName(), this.ctrl );
		startActivity( intent );
	}

	public void onClick( View v )
	{
		int indArretDepart = this.lstDepart.getSelectedItemPosition();
		int indArretArrivee = this.lstArrivee.getSelectedItemPosition();

		this.ctrl.setArrets( indArretDepart, indArretArrivee );

		String nomPeriode = (String)this.lstPeriode.getSelectedItem();
		Log.d("filtre", nomPeriode);
		this.ctrl.setPeriode( nomPeriode );

		String nomJour = (String)this.lstJour.getSelectedItem();
		Log.d("filtre", nomJour);
		this.ctrl.setJour( nomJour );

		this.ouvrirTrajetsActivity();
	}
}