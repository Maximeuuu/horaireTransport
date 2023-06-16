public class TestTemps
{
	public static void main( String[] args )
	{
		Temps t;

		sop("Test 1 : 8h50");
		t = new Temps(8,50);
		sop( t );
		t = new Temps(7,110);
		sop( t );

		sop("");

		sop("Test 2 : 2h20 et 0h20");
		t = new Temps(140);
		sop( t );
		t = new Temps(20);
		sop( t );

		sop("");

		sop("Test 3 : 2h30 et 0h30");
		t = new Temps(2.50);
		sop( t );
		t = new Temps(0.50);
		sop( t );

		sop("");

		sop("Test 4 : 18h15");
		t = new Temps("18h15");
		sop( t );
		t = new Temps("18:15");
		sop( t );

		sop("");

		sop("Test 5 : 00h50");
		t = new Temps("00:50");
		sop( t );
		t = new Temps("0:50");
		sop( t );

		sop("");

		sop("Test 6 : 7h00");
		t = new Temps("7:00");
		sop( t );
		t = new Temps("07:00");
		sop( t );
		t = new Temps("07h");
		sop( t );
		t = new Temps("7h");
		sop( t );

		sop("");

		sop("Test 7 : 11h29");
		t = new Temps("11heure29", "heure");
		sop( t );

		sop("");

		sop("Test 8 : 12h12");
		t = new Temps( new Temps("12h12"));
		sop( t );

		sop("");

		sop("Test 9 : 6h30");
		t = new Temps("6:30");
		sop( t.toString() );
		sop( t.toString4Digits() );

	}

	public static void sop( Object o ){System.out.println( o.toString() ); }
}
