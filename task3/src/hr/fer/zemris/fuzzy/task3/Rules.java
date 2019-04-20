package hr.fer.zemris.fuzzy.task3;

import hr.fer.zemris.fuzzy.task1.domains.Domain;
import hr.fer.zemris.fuzzy.task1.domains.DomainElement;
import hr.fer.zemris.fuzzy.task1.domains.IDomain;
import hr.fer.zemris.fuzzy.task1.sets.CalculatedFuzzySet;
import hr.fer.zemris.fuzzy.task1.sets.IFuzzySet;
import hr.fer.zemris.fuzzy.task1.sets.MutableFuzzySet;
import hr.fer.zemris.fuzzy.task1.sets.StandardFuzzySets;

public class Rules {

static final IDomain distanceDomain = Domain.intRange(0, 1300);
	
	static final IDomain angleDomain = Domain.intRange(-90, 91);
	
	static final IDomain akcelerationDomain = Domain.intRange(-100, 101);
	
	static final IDomain speedDomain = Domain.intRange(0, 200);
	
	static final IDomain directionDomain = Domain.intRange(0, 2);
	
	public static final IFuzzySet sudar = new CalculatedFuzzySet(
			distanceDomain, 
			StandardFuzzySets.lFunction(
					distanceDomain.indexOfElement(DomainElement.of(19)), 
					distanceDomain.indexOfElement(DomainElement.of(20))
					)
			);

	public static final IFuzzySet kriticnoBlizu = new CalculatedFuzzySet(
			distanceDomain, 
			StandardFuzzySets.lFunction(
					distanceDomain.indexOfElement(DomainElement.of(25)), 
					distanceDomain.indexOfElement(DomainElement.of(55))
					)
			);
	
	public static final IFuzzySet blizu = new CalculatedFuzzySet(
			distanceDomain, 
			StandardFuzzySets.lFunction(
					distanceDomain.indexOfElement(DomainElement.of(45)), 
					distanceDomain.indexOfElement(DomainElement.of(100))
					)
			);
	
	public static final IFuzzySet relativnoBlizu = new CalculatedFuzzySet(
			distanceDomain, 
			StandardFuzzySets.lambdaFunction(
					distanceDomain.indexOfElement(DomainElement.of(140)),
					distanceDomain.indexOfElement(DomainElement.of(145)),
					distanceDomain.indexOfElement(DomainElement.of(150))
					)
			);
	
	public static final IFuzzySet daleko = new CalculatedFuzzySet(
			distanceDomain, 
			StandardFuzzySets.gammaFunction(
					distanceDomain.indexOfElement(DomainElement.of(150)), 
					distanceDomain.indexOfElement(DomainElement.of(500)) 
					)
			);
	
	public static final IFuzzySet jakoDaleko = new CalculatedFuzzySet(
			distanceDomain, 
			StandardFuzzySets.gammaFunction(
					distanceDomain.indexOfElement(DomainElement.of(500)), 
					distanceDomain.indexOfElement(DomainElement.of(1000)) 
					)
			);
	
	public static final IFuzzySet brzo = new CalculatedFuzzySet(
			speedDomain, 
			StandardFuzzySets.gammaFunction(
					speedDomain.indexOfElement(DomainElement.of(35)), 
					speedDomain.indexOfElement(DomainElement.of(50))
					)
			);
	
	public static final IFuzzySet sporo = new CalculatedFuzzySet(
			speedDomain, 
			StandardFuzzySets.lFunction(
					speedDomain.indexOfElement(DomainElement.of(15)), 
					speedDomain.indexOfElement(DomainElement.of(50))
					)
			);
	
	public static final IFuzzySet kriviSmjer = new MutableFuzzySet(directionDomain)
			.set(DomainElement.of(0), 1);
	
	public static final IFuzzySet okretDesno = new MutableFuzzySet(angleDomain)
			.set(DomainElement.of(-90), 1);
	
	public static final IFuzzySet okretLijevo = new MutableFuzzySet(angleDomain)
			.set(DomainElement.of(90), 1);
	
	public static final IFuzzySet oštroDesno = new MutableFuzzySet(angleDomain)
			.set(DomainElement.of(-75), 1);
	
	public static final IFuzzySet desno = new MutableFuzzySet(angleDomain)
			.set(DomainElement.of(-50), 1);
	
	public static final IFuzzySet blagoDesno = new MutableFuzzySet(angleDomain)
			.set(DomainElement.of(-30), 1);
	
	public static final IFuzzySet oštroLijevo = new MutableFuzzySet(angleDomain)
			.set(DomainElement.of(75), 1);
	
	public static final IFuzzySet lijevo = new MutableFuzzySet(angleDomain)
			.set(DomainElement.of(50), 1);
	
	public static final IFuzzySet blagoLijevo = new MutableFuzzySet(angleDomain)
			.set(DomainElement.of(30), 1);
	
	public static final IFuzzySet topAcc = new MutableFuzzySet(akcelerationDomain)
			.set(DomainElement.of(100), 1);
	
	public static final IFuzzySet ubrzaj = new MutableFuzzySet(akcelerationDomain)
			.set(DomainElement.of(2), 1);
	
	public static final IFuzzySet uspori = new MutableFuzzySet(akcelerationDomain)
			.set(DomainElement.of(-1), 1);
	
	public static final IFuzzySet jaceUbrzaj = new MutableFuzzySet(akcelerationDomain)
			.set(DomainElement.of(30), 1);
	
	public static final IFuzzySet jaceUspori = new MutableFuzzySet(akcelerationDomain)
			.set(DomainElement.of(-15), 1);
	
	public static final IFuzzySet jakoUbrzaj = new CalculatedFuzzySet(akcelerationDomain, 
			StandardFuzzySets.lambdaFunction(
					akcelerationDomain.indexOfElement(DomainElement.of(50)), 
					akcelerationDomain.indexOfElement(DomainElement.of(75)), 
					akcelerationDomain.getCardinality()-1));
	
	public static final IFuzzySet jakoUspori = new CalculatedFuzzySet(akcelerationDomain, 
			StandardFuzzySets.lambdaFunction(
					akcelerationDomain.indexOfElement(DomainElement.of(-100)), 
					akcelerationDomain.indexOfElement(DomainElement.of(-75)), 
					akcelerationDomain.indexOfElement(DomainElement.of(-75))));
}
