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
	private Spinner lstPassage; //mettre une list par la suite
	private Spinner lstPeriode;
	private Spinner lstJour;
	private Button btnAjouter;
	private Button btnRetirer;
	private Button btnValider;
	private Button btnReset;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filtre);

		Intent intent = getIntent();
		this.ctrl = (Controleur) intent.getSerializableExtra(Controleur.class.getName());

		this.addComposants();
		this.retirerPassage();
		this.reset();
		this.addListeners();
	}

	private void addComposants()
	{
		this.lstDepart = findViewById( R.id.spinnerDepart );
		this.lstArrivee = findViewById( R.id.spinnerArrivee );
		this.lstPassage = findViewById( R.id.spinnerPassage );

		this.lstPeriode = findViewById( R.id.spinnerPeriode );
		this.lstJour = findViewById( R.id.spinnerJour );

		this.btnRetirer = findViewById( R.id.buttonRmPassage );
		this.btnAjouter = findViewById( R.id.buttonAddPassage );
		this.btnValider = findViewById( R.id.buttonValider );
		this.btnReset = findViewById( R.id.buttonReset );
	}

	private void addListeners()
	{
		this.btnAjouter.setOnClickListener( this );
		this.btnRetirer.setOnClickListener( this );
		this.btnValider.setOnClickListener( this );
		this.btnReset.setOnClickListener( this );
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
		if( v == btnRetirer )
			this.retirerPassage();

		if( v == btnAjouter )
			this.ajouterPassage();

		if( v == btnReset )
			this.reset();

		if( v == btnValider )
			this.valider();
	}

	private void retirerPassage()
	{
		this.btnRetirer.setVisibility( View.INVISIBLE );
		this.btnAjouter.setVisibility( View.VISIBLE );

		this.lstPassage.setVisibility( View.INVISIBLE );
		this.lstPassage.setEnabled(false);
	}

	private void ajouterPassage()
	{
		this.btnAjouter.setVisibility( View.INVISIBLE );
		this.btnRetirer.setVisibility( View.VISIBLE );

		this.lstPassage.setVisibility( View.VISIBLE );
		this.lstPassage.setEnabled(true);
	}

	private void reset()
	{
		remplirListe( this.ctrl.getEnsArret(), this.lstDepart );
		remplirListe( this.ctrl.getEnsArret(), this.lstArrivee );
		remplirListe( this.ctrl.getEnsArret(), this.lstPassage );

		//this.lstArrivee.setSelection( this.lstArrivee.getLastVisiblePosition() ); //TODO: ne marche pas
		remplirListe( this.ctrl.getEnsPeriode(), this.lstPeriode );
		remplirListe( this.ctrl.getEnsJour(), this.lstJour );
	}

	private void valider()
	{
		int indArretDepart = this.lstDepart.getSelectedItemPosition();
		int indArretArrivee = this.lstArrivee.getSelectedItemPosition();
		int indArretPassage = this.lstPassage.getSelectedItemPosition();

		this.ctrl.setArrets( indArretDepart, indArretArrivee );

		if( this.lstPassage.isEnabled() )
			this.ctrl.addArretPassage( indArretPassage );

		String nomPeriode = (String)this.lstPeriode.getSelectedItem();
		this.ctrl.setPeriode( nomPeriode );

		String nomJour = (String)this.lstJour.getSelectedItem();
		this.ctrl.setJour( nomJour );

		this.ouvrirTrajetsActivity();
	}
}