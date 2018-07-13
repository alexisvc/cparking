package com.dna.cparking.domain.imp;

import org.springframework.stereotype.Component;

import com.dna.cparking.domain.Gatekeeper;

@Component
public class GatekeeperImp implements Gatekeeper{
	public boolean checkPlateStartWithA (String plate) {
		return plate.startsWith("A");
	}
}
