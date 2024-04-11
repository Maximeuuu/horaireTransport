package fr.transport.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import fr.transport.Controleur;
import fr.transport.mobileapp.R;
import fr.transport.modele.entite.Heure;
import fr.transport.modele.entite.Lieu;
import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.Trajet;

public class TrajetsActivity extends AppCompatActivity
{
	private Controleur ctrl;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trajets);

		Intent intent = getIntent();
		this.ctrl = (Controleur) intent.getSerializableExtra("controleur");

		Log.println(Log.DEBUG, "TrajetActivity", "Génération de la page");

		this.afficherTableau();

		Log.println(Log.DEBUG, "TrajetActivity", "Fin de la page");
	}

	/*private void afficherTableau( )
	{
		List<String> ensArret = this.ctrl.getEnsArret();

		TableLayout tableLayout = findViewById( R.id.tableLayoutTrajets );

		TableRow tableRow = new TableRow( this );
		for( String arret : ensArret )
		{
			TextView textView = new TextView( this );
			textView.setText( arret );
			textView.setPadding( 10, 10, 10, 10 );
			tableRow.addView( textView ); // Ajout de la cellule à la ligne
		}
		tableLayout.addView( tableRow ); // Ajout de la ligne au tableau

		List<Trajet> ensTrajet = this.ctrl.getLigneTransport().getEnsTrajet();
		List<Lieu> ensLieu = this.ctrl.getLigneTransport().getEnsArretOrdonne();
		ensTrajet.get(0).getHeureArret( ensLieu.get(0) ); //premiere colone
		ensTrajet.get(0).getHeureArret( ensLieu.get(1) ); //deuxiem colonne

		...
	}*/

	private void afficherTableau()
	{
		Log.d( "tableauTrajet", this.ctrl.getLigneTransport().toString() );
		/*List<String> ensArret = this.ctrl.getEnsArret();
		List<Trajet> ensTrajet = this.ctrl.getLigneTransport().getEnsTrajet();
		List<Lieu> ensLieu = this.ctrl.getLigneTransport().getEnsArretOrdonne();

		TableLayout tableLayout = findViewById(R.id.tableLayoutTrajets);

		// Ajout des en-têtes de colonnes (arrêts)
		TableRow headerRow = new TableRow(this);
		for (String arret : ensArret) {
			TextView textView = new TextView(this);
			textView.setText(arret);
			textView.setPadding(10, 10, 10, 10);
			headerRow.addView(textView); // Ajout de la cellule à la ligne
		}
		tableLayout.addView(headerRow); // Ajout de la ligne d'en-têtes au tableau

		// Ajout des données de trajet pour chaque trajet
		for (Trajet trajet : ensTrajet) {
			TableRow dataRow = new TableRow(this);
			for (Lieu lieu : ensLieu) {
				Heure heureArret = trajet.getHeureArret(lieu); // Obtention de l'heure d'arrêt pour le lieu donné
				TextView textView = new TextView(this);
				textView.setText(heureArret.toString());
				textView.setPadding(10, 10, 10, 10);
				dataRow.addView(textView); // Ajout de la cellule à la ligne
			}
			tableLayout.addView(dataRow); // Ajout de la ligne de données au tableau
		}*/
	}

}