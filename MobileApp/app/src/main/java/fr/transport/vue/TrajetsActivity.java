package fr.transport.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import fr.transport.Controleur;
import fr.transport.mobileapp.R;
import fr.transport.modele.entite.Heure;
import fr.transport.modele.entite.Lieu;
import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.RechercheTrajet;
import fr.transport.modele.entite.Trajet;
import fr.transport.modele.outils.UtilitaireTableau;

public class TrajetsActivity extends AppCompatActivity
{
	private Controleur ctrl;

	private TableLayout tableHoraires;
	private TextView lblDepart;
	private TextView lblPassage;
	private TextView lblArrivee;
	private TextView lblJour;
	private TextView lblPeriode;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trajets);

		Intent intent = getIntent();
		this.ctrl = (Controleur) intent.getSerializableExtra(Controleur.class.getName());

		this.tableHoraires = findViewById( R.id.tblHoraires );
		this.lblDepart = findViewById( R.id.lblDepart );
		this.lblPassage = findViewById( R.id.lblPassage );
		this.lblArrivee = findViewById( R.id.lblArrivee );
		this.lblJour = findViewById( R.id.lblJour );
		this.lblPeriode = findViewById( R.id.lblPeriode );

		this.afficherInfosRecherche();
		this.afficherTableau();
	}

	public void afficherInfosRecherche()
	{
		RechercheTrajet recherche = this.ctrl.getRechercheTrajet();

		List<Lieu> ensPasssage = recherche.getEnsArretPassage();
		if( ensPasssage.size() >= 1 )
		{
			this.lblPassage.setText( recherche.getEnsArretPassage().get(0).getNom() );
		}
		else
		{
			this.lblPassage.setText( "" );
			this.lblPassage.setVisibility( View.INVISIBLE );
			findViewById( R.id.lblLibPassage ).setVisibility( View.INVISIBLE );
		}

		this.lblDepart.setText( recherche.getArretDepart().getNom() );
		this.lblArrivee.setText( recherche.getArretDestination().getNom() );
		this.lblJour.setText( recherche.getJour().getNom() );
		this.lblPeriode.setText( recherche.getPeriode().getNom() );
	}

	public void afficherTableau()
	{
		this.tableHoraires.removeAllViews();

		TableRow tableRow;
		tableRow = this.creerEntete( this.ctrl.getNbArretsPassage() );
		this.tableHoraires.addView( tableRow );

		Log.d( "TrajetsActivity", "filtrer");
		List<Heure[]> ensHoraireTrajet = this.ctrl.filtrerEnListe();
		Log.d( "TrajetsActivity", ensHoraireTrajet.size()+"");
		for( Heure[] ensHeure : ensHoraireTrajet )
		{
			tableRow = this.creerLigneHeures( ensHeure );
			this.tableHoraires.addView( tableRow );
			Log.d( "TrajetsActivity", ensHeure.length+"");
		}
	}

	private TableRow creerEntete( int nbArrets )
	{
		TableRow tableRow = new TableRow( this );

		TextView lblTitre = this.creerColonneEntete( "Départ");
		tableRow.addView( lblTitre );

		int nbArretsPassage =  nbArrets; //- 2
		for( int cptArrets=0; cptArrets < nbArretsPassage; cptArrets++ )
		{
			lblTitre = this.creerColonneEntete( "Passage " + (cptArrets + 1));
			tableRow.addView( lblTitre );
		}

		lblTitre = this.creerColonneEntete( "Arrivée" );
		tableRow.addView( lblTitre );

		tableRow.setGravity( View.TEXT_ALIGNMENT_GRAVITY );

		return tableRow;
	}

	private TextView creerColonneEntete( String nom )
	{
		TextView lblTitre = this.creerCellule( nom );
		return lblTitre;
	}

	private TableRow creerLigneHeures( Heure[] ensHeure )
	{
		TableRow tableRow = new TableRow( this );

		for( Heure heure : ensHeure )
		{
			TextView lblHeure = this.creerCelluleHeure( heure.toString() );
			tableRow.addView( lblHeure );
		}

		tableRow.setGravity( View.TEXT_ALIGNMENT_GRAVITY );

		return tableRow;
	}

	private TextView creerCelluleHeure( String nom )
	{
		TextView lblTitre = this.creerCellule( nom );
		return lblTitre;
	}

	private TextView creerCellule( String nom )
	{
		TextView lblTitre;
		lblTitre = new TextView( this );
		lblTitre.setText( nom );
		lblTitre.setTextAlignment( View.TEXT_ALIGNMENT_CENTER );

		TrajetsActivity.ajouterMarges( lblTitre );

		return lblTitre;
	}

	private static void ajouterMarges( TextView lbl )
	{
		TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(10, 10, 10, 10);
		lbl.setLayoutParams(layoutParams);
	}
}