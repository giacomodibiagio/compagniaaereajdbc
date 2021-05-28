package main;

import java.util.Date;

import main.dao.*;
import main.model.Aereo;
import main.model.Aereoporto;
import main.model.Biglietto;
import main.model.Passeggero;
import main.model.Pilota;
import main.model.Volo;


public class ConnessioneMain {
	public static void main(String[] args) {
		
		CompagniaAerea compagnia = CompagniaAerea.getIstance();
		compagnia.setNomeCompagnia("Alitalia");
		
		/*
		Pilota p = new Pilota();		
		p.setStipendio(5000);
		p.setNome("Andrea");		
		p.setCognome("Tordi");		
		p.setIdStatoPilota(1);
		compagnia.assumerePilota(p);*/
		
		Aereo a = new Aereo();			
		a.setIdStatoAereo(3);
		//compagnia.acquistaAereo(a);
		
		Passeggero p = new Passeggero();
		p.setNome("Maurizio");		
		p.setCognome("Cirotti");	
		p.setCodiceFiscale("wfgsdgsd");		
		//compagnia.registraNuovoPasseggero(p);
		
		Volo v = new Volo();		
		v.setGiornoDelVolo(new Date(2020-1900, 1, 22));	
		v.setIdAereo(4);
		v.setIdPilota(5);
		v.setIdAereoportoPartenza(3);
		v.setIdAereoportoArrivo(4);
		//compagnia.organizzareNuovoVolo(v);		
		
		Aereo ap = new Aereo();
		ap.setIdAereo(3);
		//compagnia.modificaStatoAereo(a);
		
		Pilota p1 = new Pilota();
		p1.setIdPilota(4);
		//compagnia.modificaStatoPilota(p1.getIdPilota());
		
		Volo v2 = new Volo();
		v2.setIdVolo(4);
		//compagnia.modificaDatiVolo(v2.getIdVolo());
		
		/* 1) QUANTI SONO I PILOTI IN ATTIVITA
		PilotaDaoImpl pilotaDao = new PilotaDaoImpl();
		pilotaDao.countPiloti();
		
		// 2) quanti passeggeri sono in volo da una certa località?
		VoloDaoImpl v = new VoloDaoImpl();
		Volo vl = new Volo();
		vl = v.get(5);
		VoloDaoImpl voloDao = new VoloDaoImpl();
		voloDao.countPassFrom(vl);

		// 3) quali aerei sono in volo in una certa data e ora?
		 * Date d1 = new Date(2008-1900,1,20,15,0,0);
		//compagnia.getAereiInVolo();
		 
		// 4 qual è il passeggero che ha volato più spesso con la compagnia?
		PasseggeroDaoImpl p = new PasseggeroDaoImpl();
		p.getPasseggeroMaxViaggi();
		
		 
		//#5 - 6 qual è il passeggero che ha speso di più volando con la compagnia?
		PasseggeroDaoImpl p = new PasseggeroDaoImpl();
		p.getPasseggeroMax();
		p.getPasseggeroMin();
		
		// 7 quale pilota ha volato di più?
		PilotaDaoImpl pilotaDao = new PilotaDaoImpl();
		pilotaDao.getPilotaMaxOre();
		
		// #8 qual è il volo che ha incassato di più?
		VoloDaoImpl v = new VoloDaoImpl();
		v.getVoloProficuo();
		
		// #9 quali sono i voli per cui la compagnia ci ha rimesso in un dato giorno? (guadagno negativo)		
		Date d1 = new Date(2008-1900,1,20);
		VoloDaoImpl voloDao = new VoloDaoImpl();
		voloDao.getVoliNegativi(d1);					
	
		
		// # 10 quali sono gli aeroporti dove un certo aereo è atterrato negli ultimi x giorni?
		AereoDaoImpl a = new AereoDaoImpl();
		Date d1 = new Date(2008-1900,1,1);
		Date d2 = new Date(2008-1900,1,22);
		a.getDoveAtterrato(2, d1, d2);		
		
		// #11 da quale aeroporto sono partiti più aerei il giorno x?
		Date d = new Date(2008-1900,1,20);
		AereoportoDaoImpl a = new AereoportoDaoImpl();
		a.getAereoportoMaxPartiti(d);
		
		// #12 chi sono i passeggeri del volo x? (nome, cognome, codice fiscale...)
		VoloDaoImpl v = new VoloDaoImpl();
		v.getPasseggeriInVolo(4);
		
		// #13 quanti passeggeri sono previsti in arrivo un dato giorno al dato aeroporto?
		VoloDaoImpl v = new VoloDaoImpl();
		Date d = new Date(2008-1900,1,20);		
		v.getPasseggeriPrevisti(3, d);*/
		
		// #14 quanto ha guadagnato la compagnia?
		//compagnia.getGuadagnoCompagnia();
		

	}
}
