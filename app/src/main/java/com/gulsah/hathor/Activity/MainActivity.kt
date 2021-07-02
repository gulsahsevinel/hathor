package com.gulsah.hathor.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.gulsah.hathor.R
import com.gulsah.hathor.databinding.ActivityMainBinding
import com.gulsah.hathor.entity.*
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.ProductsDaoInterface
import com.gulsah.hathor.retrofit.UsersDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var pdao: ProductsDaoInterface
    private lateinit var udao: UsersDaoInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.navController)

        pdao = ApiUtils.getProductsDaoInterface()
        udao = ApiUtils.getUsersDaoInterface()


        //kayit()
        //sil()
        //tumUrunler()
        //uyeOl()
        //giris()


    }

    fun uyeOl() {
        udao.uye_ol("gulsahsevinel@gmail.com", "gulsah123", "Gülşah Sevinel", "123456789")
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                    Log.e("response", response.body()!!.success.toString())
                    Log.e("mesaj", response.body()!!.message)
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    /*fun giris (){
        udao.giris_yap("gulsahsevinel@gmail.com","gulsah123").enqueue(object : Callback<UsersResponse>{
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                val u = response.body()!!.users
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }*/

    fun tumUrunler() {
        pdao.getProducts("gulsahsevinel").enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                val urunlerListesi = response.body()!!.urunler
                for (k in urunlerListesi) {
                    Log.e("***********", "***********")
                    Log.e(" mesaj", "geldi")
                    Log.e(" adi", k.urun_adi)
                    Log.e(" id", k.product_id.toString())
                    Log.e(" sepet", k.sepet_durum.toString())
                }

            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun kayit(
        satici_adi: String,
        urun_adi: String,
        urun_fiyat: Double,
        urun_aciklama: String,
        urun_gorsel_url: String,
        sepet_durum: Int,
        urun_indirimli_mi: Int
    ) {
        pdao.urunEkle(
            satici_adi,
            urun_adi,
            urun_fiyat,
            urun_aciklama,
            urun_gorsel_url,
            sepet_durum,
            urun_indirimli_mi
        )
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                    Log.e("response", response.body()!!.success.toString())
                    Log.e("mesaj", response.body()!!.message)
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })
    }

    fun sil() {
        pdao.sepetDurumDegistir(5).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                Log.e("response", response.body()!!.success.toString())
                Log.e("mesaj", response.body()!!.message)
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {}
        })
    }

}
/*var urun_adi = listOf<String>(
            "Hyaluronic Acid",
            "Mineral Uv Filters",
            "Niacinamide Powder",
            "L-ascorbic Acid Powder",
            "Salicylic Acid",
            "Natural Moisturizing",
            "Glycolic Acid",
            "Vitamin C",
            "Azelaic Acid",
            "Lactic Acid",
            "Alpha Arbutin",
            "Niacinamide",
            "Anti-aging - Buffet",
            "Argireline Solution",
            "Matrixyl 10% + Ha"
        )
        var urun_fiyat = listOf<Double>(
            131.99,
            248.99,
            102.59,
            289.00,
            199.99,
            124.99,
            239.99,
            119.99,
            319.00,
            119.99,
            149.99,
            129.99,
            239.99,
            140.00,
            193.99
        )
        var urun_aciklama =
            listOf<String>(
                "Hathor Hyaluronic Acid 2% + B5 30 ml Ultra Saf, Vegan Hyaluronik Asitli Hidrasyon Destek FormülüHyaluronik Asit (HA), sudaki ağırlığının 1000 katına kadar çekebilir Bu formülasyon, düşük, orta ve yüksek moleküler ağırlıklı HA’nın yanı sıra, çok derinlikli hidrasyon için% 2’lik bir birleşik konsantrasyondaki yeni nesil HA çapraz polimerini birleştirir Bu sistem, yüzey nemlendirmesini de artıran B5 Vitamini ilavesiyle desteklenir",
                "Hathor Mineral UV Filters SPF 15 with Antioxidants Antioksidan içeren 15 faktör güneş koruma 50ml Olağan Mineral UV Filtresi formülleri yelpazesi, antioksidan, nemlendirme ve tahriş etmeyen yapısı ile birlikte SPF koruması sunar.",
                "Niasinamid (B3 vitamini olarak da bilinir), sebumu dengelemekten ve cilt bariyerini güçlendirmekten genişlemiş gözenekleri, düzensiz cilt dokusunu ve aşırı parlaklığı hedeflemeye kadar cilt için birçok faydası olan aktif bir bileşendir.",
                "C vitamini, cilt tonunu aydınlatan ve yaşlanma belirtilerinin görünümünü azaltan etkili bir antioksidandır. Bu formül, düz olmayan cilt tonu, donukluk ve yaşlanma belirtileri görünümünü gözle görülür şekilde hedefleyen ve diğer tedavilerle karıştırılmak üzere formüle edilmiş çok ince bir L-Askorbik Asit tozu sunar.",
                "Salisilik Asit, cildi temizleyen bir beta hidroksi asittir. Bu %2'lik tedavi çözümü, lekelerin görünümüyle savaşmak ve sürekli kullanımda daha iyi görünen cilt berraklığı için gözeneklerin iç duvarlarının dökülmesine yardımcı olur.",
                "Doğal nemlendirici faktörü (genellikle çok kuru,nemsiz cilt için) bileşenlerinin doğrudan topikal eklenmesiyle,yağsız formülü sürekli kullanımda hem anında etki eder hem de kalıcı sonuçlar sunar.",
                "Glikolik asit, cildi temizleyen bir alfa hidroksi asittir Bu% 7 tonlama çözeltisi, cildin parlaklığını ve görünür berraklığını arttırmak için hafif bir peeling sağlar. Formül ayrıca sürekli kullanımla cilt dokusunun görünümünü de iyileştirir.",
                "Vitamin C Suspension 23% + HA Spheres 2% 30ml Susuz, Silikonsuz Kararlı Bir Süspansiyon C vitamini, cilt tonunu aydınlatan ve yaşlanma belirtilerinin görünümünü azaltan etkili bir antioksidandır.",
                "Çok Fonksiyonlu Aydınlatıcı Formül Tahıllarda bulunan Azelaik Asit, normal ciltte yaşayan maya tarafından doğal olarak üretilir. Cilt dokusunun düzgünlüğünü gözle görülür şekilde iyileştirirken ve lekelerin görünümünü de azaltarak cilt tonunu aydınlatır.",
                "Lactic Acid 10% + HA 30ml Yüksek Mukavemetli Laktik Asit Yüzeysel Soyma Formülasyonu Laktik Asit, cildi temizleyen bir alfa hidroksi asittir. Bu %10'luk formülasyon hafif bir pul pul dökülme sağlar ve genellikle pul pul dökülme ile ilişkili iltihaplanma ve hassasiyet belirtilerini azalttığı bilinen saflaştırılmış bir Tasmanya biberiyle desteklenir.",
                "Alfa Arbutin, Pigmentli ve yara izlerine sahip ciltler için en iyisidir.Alfa Arbutin güvenli bir cilt aydınlatıcı maddedir. Alfa Arbutin, Sivilcelerin bıraktığı kırmızı ve siyahlaşmış izlerin solmasına yardımcı olur.Alfa arbutinin uygun şekilde emilmesi için hyaluronik asit içerir.",
                "Niacinamide (b3 vitamini), cilt lekeleri ve tıkanıklık görünümünü azaltmak için endikedir. Bu vitaminin %10'luk yüksek bir konsantrasyonu, sebum aktivitesinin görünür yönlerini dengelemek için pirolidon karboksilik asidin çinko tuzu ile formülde desteklenir",
                "Çok yönlü olan bu serum yaşlanmanın birçok belirtisini ele alır. Çoklu peptid kompleksleri, bu hafif serumda, çok sayıda yarar sağlamak için birleşir; Matrixyl 3000, kollajen sentezini ve hyaluronik asidi uyarmak için, ince çizgilerin “dolgun” hale gelmesini sağlar.",
                "Peptid kompleksi içeren hafif bir serumdur. Göz çevresi ve alın bölgesi dahil olmak üzere dinamik kıvrımlar geliştirmeye yatkın yüz bölgelerinin görünümünü iyileştirmeye yardımcı olur.",
                "Hathor Matrixyl 10% + HA 30ml Yüksek Mukavemetli Peptit Formülasyonu Matrixyl, Fransa'da Sederma tarafından geliştirilen ticari bir peptid bileşimidir. Bu son derece etkili kompozisyonun statik ve dinamik kırışıklıkların görünümünü azalttığı gösterilmiştir."
            )
        var urun_gorsel_url =
            listOf<String>(
                "19axJoqWvqW-HCmQvrcnFX_VVTghjJYQi",
                "1TK31YkgAkXgki43znfJuFLnfCrDUbUqT",
                "1rWNjM98w8shZRmZ0VFTx45XDk1eSuVYP",
                "1Ubii2pz2eUXcYaH8jmnDkioZsel07Xn-",
                "18_A-adDkdOheeQjmntZ_RTf895oc8zJb",
                "1L3TK1jCzmt4-sBoM1d9QnCHzEVfa-8Eq",
                "1NopMUNrbx6j7fOajIN2DK7wF4SxSLOAl",
                "1tAL6npK-O3JLkcN8xF0SV0hvUon33v0Q",
                "1ul_Zrgm3QD0NrALbAK1ZmP81qEUgkJ5S",
                "1C3CfsT95dVrDM6kHklwqWfQ-NUewmJ2N",
                "19axJoqWvqW-HCmQvrcnFX_VVTghjJYQi",
                "19axJoqWvqW-HCmQvrcnFX_VVTghjJYQi",
                "1C3CfsT95dVrDM6kHklwqWfQ-NUewmJ2N",
                "19axJoqWvqW-HCmQvrcnFX_VVTghjJYQi",
                "19axJoqWvqW-HCmQvrcnFX_VVTghjJYQi"
            )*/

/*for (i in 0..(urun_adi.size-1)){
    kayit("gulsahsevinel",urun_adi[i],urun_fiyat[i],urun_aciklama[i],urun_gorsel_url[i],0,0)
}*/