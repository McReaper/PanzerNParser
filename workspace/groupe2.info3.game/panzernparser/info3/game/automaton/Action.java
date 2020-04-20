package info3.game.automaton;

import java.util.List;
import java.util.ListIterator;

import info3.game.model.entities.Entity;



public class Action {
	List<FunCall> m_funCalls;
	
	/* Exemple de roue de la fortune :
	 * 25% Move / 10% Egg / Protect / 15% Wizz Donne :
	 * [25,35,85,100]
	 * 25% Move / 10% Egg Donne :
	 * [25,35]
	 * Comment la lire :
	 * Soit x un entier entre 1 et 100
	 * Soit i = 0
	 * Si i > taille du tab -> on n'a tiré personne
	 * Si x > ième case -> i++ et on recommence
	 * Sinon On a tiré la ième func
	 */
	
	int[] m_roueDeLaFortune;
	
	Action(List<FunCall> funCalls){
		m_funCalls = funCalls;
		m_roueDeLaFortune = new int[m_funCalls.size()];
		
		//Initialisation de la roue de la fortune.
		int nbFuncWithoutPercent = 0;
		int percentNoneAffected = 100;
		for (FunCall funCall : m_funCalls) {
			if(funCall.m_percent == -1) {
				nbFuncWithoutPercent++;
			} else {
				percentNoneAffected -= funCall.m_percent;
			}
		}
		int i = 0;
		for (FunCall funCall : m_funCalls) {
			if(i == 0) {
				if(funCall.m_percent == -1) {
					int percentAttributed = percentNoneAffected/nbFuncWithoutPercent;
					m_roueDeLaFortune[i] = percentAttributed;
					nbFuncWithoutPercent--;
					percentNoneAffected -= percentAttributed;
				} else {
					m_roueDeLaFortune[i] = funCall.m_percent;
				}
			} else {
				if(funCall.m_percent == -1) {
					int percentAttributed = percentNoneAffected/nbFuncWithoutPercent;
					m_roueDeLaFortune[i] = m_roueDeLaFortune[i-1] + percentAttributed;
					nbFuncWithoutPercent--;
					percentNoneAffected -= percentAttributed;
				} else {
					m_roueDeLaFortune[i] = m_roueDeLaFortune[i-1] + funCall.m_percent;
				}
			}
			i++;
		}
	}
	
	boolean execute(Entity e) {
		int percent = (int)(Math.random() * 100);
		int i = 0;
		while(i < m_roueDeLaFortune.length && percent > m_roueDeLaFortune[i]) {
			i++;
		}
		if(i >= m_roueDeLaFortune.length) {
			return false;
		} else {
			m_funCalls.get(i).execut(e);
			return true;
		}
	}
}
