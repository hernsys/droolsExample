package com.plugtree.dslExample;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;

import org.drools.template.ObjectDataCompiler;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import com.plugtree.dslExample.Params.ParamSet;

public class TemplateWithJavaObjectTest extends BaseTest {
	
	@Test
	public void testFirstCase(){
		Collection<ParamSet> paramSets = new ArrayList<ParamSet>();
		// populate paramSets
		paramSets.add( new ParamSet(1, "OBPO", "not", 0, 200) );
		paramSets.add( new ParamSet(1, "OCMA", "or", 0, 400) );
		ObjectDataCompiler converter = new ObjectDataCompiler();
		InputStream templateStream = getClass().getResourceAsStream( TEMPLATE_PATH );
		String drl = converter.compile( paramSets, templateStream );
		
		
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		Reader rdr = new StringReader( drl );
		kBuilder.add( ResourceFactory.newReaderResource( rdr ), ResourceType.DRL );
		if( kBuilder.hasErrors() ){
			// ...
			throw new IllegalStateException( "DRL errors" );
		}
		kBase.addKnowledgePackages( kBuilder.getKnowledgePackages() );
		
	}
	
	

}
