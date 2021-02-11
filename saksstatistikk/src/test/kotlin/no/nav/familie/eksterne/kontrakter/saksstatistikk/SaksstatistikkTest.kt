package no.nav.familie.eksterne.kontrakter.saksstatistikk

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.worldturner.medeia.api.UrlSchemaSource
import com.worldturner.medeia.api.jackson.MedeiaJacksonApi
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*


class SaksstatistikkTest {


    private val api = MedeiaJacksonApi()
    private val jsonFactory = JsonFactory()
    private val behandlingSchemaValidator = api.loadSchema(UrlSchemaSource(
            javaClass.getResource("/schema/behandling-schema.json")))
    private val sakSchemaValidator = api.loadSchema(UrlSchemaSource(
            javaClass.getResource("/schema/sak-schema.json")))
    private val objectMapper = ObjectMapper()
            .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .registerKotlinModule()
            .registerModule(JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)


    @Test
    fun `kun required satt, skal validere mot behandling schema`() {
        val behandlingDVH = BehandlingDVH(funksjonellTid = ZonedDateTime.now(),
                                          tekniskTid = ZonedDateTime.now(),
                                          mottattDato = ZonedDateTime.now(),
                                          registrertDato = ZonedDateTime.now(),
                                          behandlingId = "behandlingId",
                                          sakId = "sakId",
                                          behandlingType = "behandlingType",
                                          behandlingStatus = "behandlingStatus",
                                          behandlingKategori = "ORDINÆR",
                                          behandlingAarsak = "SØKNAD",
                                          automatiskBehandlet = false,
                                          utenlandstilsnitt = "EØS",
                                          ansvarligEnhetKode = "kode",
                                          behandlendeEnhetKode = "kode",
                                          ansvarligEnhetType = "ansvarligEnhetType",
                                          behandlendeEnhetType = "behandlendeEnhetType",
                                          totrinnsbehandling = true,
                                          avsender = "avsender",
                                          versjon = "2",
                                          funksjonellId = UUID.randomUUID().toString())

        val validatedParser = api.decorateJsonParser(behandlingSchemaValidator,
                                                     jsonFactory.createParser(objectMapper.writeValueAsBytes(behandlingDVH)))
        api.parseAll(validatedParser)
    }

    @Test
    fun `alle parametere satt, skal validere mot behandling schema`() {
        val behandlingDVH = BehandlingDVH(funksjonellTid = ZonedDateTime.now(),
                                          tekniskTid = ZonedDateTime.now(),
                                          mottattDato = ZonedDateTime.now(),
                                          registrertDato = ZonedDateTime.now(),
                                          behandlingId = "behandlingId",
                                          funksjonellId = UUID.randomUUID().toString(),
                                          sakId = "sakId",
                                          behandlingType = "behandlingType",
                                          behandlingStatus = "behandlingStatus",
                                          behandlingKategori = "ORDINÆR",
                                          behandlingUnderkategori = "småbarnstillegg",
                                          behandlingAarsak = "SØKNAD",
                                          automatiskBehandlet = false,
                                          utenlandstilsnitt = "EØS",
                                          ansvarligEnhetKode = "kode",
                                          behandlendeEnhetKode = "kode",
                                          ansvarligEnhetType = "ansvarligEnhetType",
                                          behandlendeEnhetType = "behandlendeEnhetType",
                                          totrinnsbehandling = true,
                                          avsender = "avsender",
                                          versjon = "2",
                                          vedtaksDato = LocalDate.now(),
                                          relatertBehandlingId = "relatertBehandlingId",
                                          vedtakId = "vedtakId",
                                          resultat = "resultat",
                                          resultatBegrunnelser = listOf(ResultatBegrunnelseDVH(fom = LocalDate.now(),
                                                                                               tom = LocalDate.now().plusMonths(1),
                                                                                               begrunnelseType = "INNVILGET",
                                                                                               vedtakBegrunnelse = "INNVILGET_BOSATT_I_NORGE")),
                                          behandlingTypeBeskrivelse = "behandlingTypeBeskrivelse",
                                          behandlingStatusBeskrivelse = "behandlingStatusBeskrivelse",
                                          utenlandstilsnittBeskrivelse = "utenlandstilsnittBeskrivelse",
                                          beslutter = "beslutter",
                                          saksbehandler = "saksbehandler",
                                          behandlingOpprettetAv = "behandlingOpprettetAv",
                                          behandlingOpprettetType = "behandlingOpprettetType",
                                          behandlingOpprettetTypeBeskrivelse = "behandlingOpprettetTypeBeskrivelse"
        )

        val validatedParser = api.decorateJsonParser(behandlingSchemaValidator,
                                                     jsonFactory.createParser(objectMapper.writeValueAsBytes(behandlingDVH)))
        api.parseAll(validatedParser)
    }

    @Test
    fun `skal validere mot sak schema`() {
        val sakDVH = SakDVH(funksjonellTid = ZonedDateTime.now(),
                            tekniskTid = ZonedDateTime.now(),
                            opprettetDato = LocalDate.now(),
                            funksjonellId = UUID.randomUUID().toString(),
                            sakId = "sakId",
                            aktorId = 123,
                            bostedsland = "NO",
                            aktorer = listOf(AktørDVH(1, "rolle", "beskrivelse")),
                            sakStatus = "sakStatus",
                            avsender = "avsender",
                            versjon = "2")

        val validatedParser = api.decorateJsonParser(sakSchemaValidator,
                                                     jsonFactory.createParser(objectMapper.writeValueAsBytes(sakDVH)))
        api.parseAll(validatedParser)
    }
}