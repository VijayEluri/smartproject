#!/bin/bash

### Escrever Builder ####
function escreverBuilder() {
	echo "package br.nom.pedro.oliveira.smartproject.integration.orm.builder;";
	echo "";
	echo "import br.nom.pedro.oliveira.smartproject.integration.orm.*;";
	echo "import com.ppm.model.Builder;";
	echo "";
	echo "public final class ${POJO}Builder extends Builder<${POJO}> {";
	echo "";
	echo "	public ${POJO}Builder() {";
	echo "		super(new ${POJO}());";
	echo "	}";
	echo "";
	echo "	public ${POJO}Builder(final ${POJO} instance) {";
	echo "		super(instance);";
	echo "	}";

	cat ${POJO}.java | grep "public void set" | while read METHODS; 
		do
		param_plus_type=$(echo ${METHODS} | cut -d "(" -f2);
		param=$(echo ${param_plus_type} | cut -d " " -f2);
		type=$(echo ${param_plus_type} | cut -d " " -f1);
		set_method=$(echo ${METHODS} | cut -d "(" -f1);
		set_method=$(echo ${set_method} | sed s/public\ void//);
		set_method=$(echo ${set_method} | tr -d " ");
		echo "	public ${POJO}Builder com${set_method:3}(final ${type} ${param} {";
		echo "		this.instance.${set_method}(${param};";
		echo "		return this;";
		echo "	}";
		echo "";
		done;
	echo "@Override";
	echo "	public ${POJO} build() {";
	echo "		return this.instance;";
	echo "	}";
	echo "";
	echo "@Override";
	echo "public void fillDefaultValues() {";
	echo "	//TODO: Implementar ";
	echo "	}";
	echo "}";
	exit 0;
}

## Main ##

ERROR="Usage ./gerarBuilder.sh [pojo_name]";
if [ "${#}" -lt 1 ];
	then
		echo $ERROR;
		exit 1;
	else
		export POJO="${1}";
fi

if [ ! -f "${POJO}.java" ];
	then 
	echo "Arquivo não encontrado";
	echo ${ERROR};
	exit 1;
fi
escreverBuilder;