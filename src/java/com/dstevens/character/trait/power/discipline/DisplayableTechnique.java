package com.dstevens.character.trait.power.discipline;

import java.util.List;
import java.util.stream.Collectors;

public class DisplayableTechnique {

	public Integer id;
	public List<DisplayableTechniqueRequirement> collect;

	@Deprecated
	//Jackson only
	public DisplayableTechnique() {
		this(null, null);
	}
	
	public DisplayableTechnique(Integer id, List<DisplayableTechniqueRequirement> collect) {
		this.id = id;
		this.collect = collect;
	}

	public static DisplayableTechnique from(Technique t) {
		return new DisplayableTechnique(t.getId(), t.requirements().stream().map((TechniqueRequirement tr) -> DisplayableTechniqueRequirement.from(tr)).collect(Collectors.toList()));
	}
	
	public Technique from(DisplayableTechnique t) {
		return Technique.values()[t.id];
	}
	
}
