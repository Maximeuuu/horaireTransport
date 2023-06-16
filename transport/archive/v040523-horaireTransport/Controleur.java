public class Controleur
{
	private Fenetre vue;
	private HorairesTransport metier;

	public Controleur()
	{
		this.metier = new HorairesTransport( "horaires/..." );
		this.vue =  new Fenetre( this );
	}

	public static void main( String[] args )
	{
		new Controleur();
	}

	public Tableur getTableur()
	{
		return this.metier.getTableur();
	}
	
	public void rechercher();
	{
		String messageErreur;
		Objet[] ensSaisie = this.vue.getEnsSaisie();
		
		try
		{
			for( int cpt=0; cpt<ensSaisie.length; cpt++ )
			{
				System.out.println( ensSaisie[cpt] );
			}
		}
		
	}
}
