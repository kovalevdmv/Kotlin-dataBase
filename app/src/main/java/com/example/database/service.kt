package com.example.database

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.lang.Exception


class service : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
    }

    fun onClickDelAll(view: View) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Удалить все слова?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            val dbHlp = dbHelper(this, null)
            dbHlp.allDel()
        }
        builder.show()
    }

    fun testContent(view: View) {

        var ret : Boolean = false

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Добавить тестовый набор слов?")
        builder.setNegativeButton(android.R.string.no) { dialog, which -> ret=true}
        builder.setPositiveButton(android.R.string.yes) { dialog, which -> addTestContent()}
        builder.show()
        if (ret)
            return
    }

    fun addTestContent(){

        Toast.makeText(this, "Добавление....", Toast.LENGTH_LONG)

        val dbHlp = dbHelper(this, null)
        val list = ArrayList<WordData>()

        list.add(WordData("определенный артикль","the [ðə:]"))
        list.add(WordData("и; а, но","and [ænd]"))
        list.add(WordData("неопределенный артикль","a [ə]"))
        list.add(WordData("к, в, на, до, для","to [tu:]"))
        list.add(WordData("был, была, было;","was [wɔz]"))
        list.add(WordData("я","I [ʌi]"))
        list.add(WordData("3- е л. ед. ч. наст. врем. гл. to be","is [iz]"))
        list.add(WordData("из, от, о, об","of [ɔv]"))
        list.add(WordData("тот, та, то","that [ðæt]"))
        list.add(WordData("ты, вы","you [ju:]"))
        list.add(WordData("он","he [hi:]"))
        list.add(WordData("он, она, оно; это","it [it]"))
        list.add(WordData("в","in [in]"))
        list.add(WordData("его","his [hiz]"))
        list.add(WordData("имел, получал","had [hæd]"))
        list.add(WordData("делать","do [du:]"))
        list.add(WordData("с, вместе с","with [wið]"))
        list.add(WordData("не, нет; ни","not [nɔt]"))
        list.add(WordData("её","her [hз:]"))
        list.add(WordData("в течение, на, для","for [fɔ:]"))
        list.add(WordData("на","on [ɔn]"))
        list.add(WordData("около, у; в, на","at [æt]"))
        list.add(WordData("только, лишь, кроме, но, а","but [bʌt]"))
        list.add(WordData("она","she [ʃi:]"))
        list.add(WordData("его","him [him]"))
        list.add(WordData("как, когда","as [æz]"))
        list.add(WordData("мн. ч. наст. врем. гл. to be","are [a:(r)]"))
        list.add(WordData("говорил, сказал","said [sed]"))
        list.add(WordData("они","they [ðei]"))
        list.add(WordData("мы","we [wi:]"))
        list.add(WordData("все, вся, всё","all [ɔ:l]"))
        list.add(WordData("этот, эта, это","this [ðis]"))
        list.add(WordData("иметь; получать; быть должным","have [hæv]"))
        list.add(WordData("там, туда, здесь","there [ðɛə]"))
        list.add(WordData("что","what [(h)wɔt]"))
        list.add(WordData("вне, снаружи; за","out [aut]"))
        list.add(WordData("наверх(у), выше; вверх по, вдоль по","up [ʌp]"))
        list.add(WordData("один","one [wʌn]"))
        list.add(WordData("от, из, с","from [frɔm]"))
        list.add(WordData("мне, меня","me [mi:]"))
        list.add(WordData("идти, ехать ; уходить","go [gəu]"))
        list.add(WordData("были","were [wз:]"))
        list.add(WordData("1) вспом. глагол.; 2) модальный глагол","would [wud]"))
        list.add(WordData("похожий; как, подобно; любить, нравиться","like [laik]"))
        list.add(WordData("когда","when [(h)wen]"))
        list.add(WordData("мог, умел","could [kud]"))
        list.add(WordData("тогда; затем","then [ðen]"))
        list.add(WordData("быть, существовать; находиться","be [bi:]"))
        list.add(WordData("их , им","them [ðem]"))
        list.add(WordData("делал, выполнял","did [did]"))
        list.add(WordData("был, была, было","been [bi:n]"))
        list.add(WordData("теперь, сейчас","now [nau]"))
        list.add(WordData("взгляд, смотреть","look [luk]"))
        list.add(WordData("спина, задний","back [bæk]"))
        list.add(WordData("мой","my [mai]"))
        list.add(WordData("нет, не","no [nəu]"))
        list.add(WordData("твой, ваш","your [jɔ:]"))
        list.add(WordData("который; что","which [(h)witʃ]"))
        list.add(WordData("кругом, вокруг; около; о, об, относительно","about [ə'baut]"))
        list.add(WordData("время; раз","time [taim]"))
        list.add(WordData("вниз, внизу; вниз по, вдоль по","down [daun]"))
        list.add(WordData("в","into ['intu:]"))
        list.add(WordData("кто; который","who [hu:]"))
        list.add(WordData("мочь; уметь","can [kæn]"))
        list.add(WordData("знать","know [nəu]"))
        list.add(WordData("если","if [if]"))
        list.add(WordData("только что","just [dʒʌst]"))
        list.add(WordData("их","their [ðɛə]"))
        list.add(WordData("получать; брать; приобретать","get [get]"))
        list.add(WordData("над; свыше","over ['əuvə]"))
        list.add(WordData("больше, более","more [mɔ:]"))
        list.add(WordData("несколько","some [sʌm]"))
        list.add(WordData("человек, мужчина","man [mæn]"))
        list.add(WordData("приходить, приезжать; случаться","come [kʌm]"))
        list.add(WordData("неопределённый артикль","an [æn]"))
        list.add(WordData("так; тоже, также","so [səu]"))
        list.add(WordData("другой, иной, еще","other ['ʌðə]"))
        list.add(WordData("маленький","little ['litl]"))
        list.add(WordData("видеть","see [si:]"))
        list.add(WordData("здесь, тут","here [hiə]"))
        list.add(WordData("вещь, предмет","thing [θiŋ]"))
        list.add(WordData("рука","hand [hænd]"))
        list.add(WordData("у , около","by [bai]"))
        list.add(WordData("1) вспом. гл. будущ. врем.; 2) модальный глагол","will [wil]"))
        list.add(WordData("путь, дорога","way [wei]"))
        list.add(WordData("опять, снова","again [ə'gein]"))
        list.add(WordData("правый; верно","right [rait]"))
        list.add(WordData("только","only ['əunli]"))
        list.add(WordData("1-е л. ед.ч. наст. врем. гл. to be","am [æm]"))
        list.add(WordData("как","how [hau]"))
        list.add(WordData("думать; считать, полагать","think [θiŋk]"))
        list.add(WordData("или","or [ɔ:]"))
        list.add(WordData("получил","got [gɔt]"))
        list.add(WordData("хороший; добро","good [gud]"))
        list.add(WordData("глаз; взгляд","eye [ai]"))
        list.add(WordData("хорошо","well [wel]"))
        list.add(WordData("думал, мысль","thought [θɔ:t]"))
        list.add(WordData("день; сутки","day [dei]"))
        list.add(WordData("два","two [tu:]"))
        list.add(WordData("чем, нежели","than [ðæn]"))
        list.add(WordData("перед; раньше","before [bi'fɔ:]"))
        list.add(WordData("где; куда","where [(h)wɛə]"))
        list.add(WordData("очень","very ['veri]"))
        list.add(WordData("говорить, сказать","say [sei]"))
        list.add(WordData("приходил, приезжал","came [keim]"))
        list.add(WordData("какой-нибудь","any ['eni]"))
        list.add(WordData("старый","old [əuld]"))
        list.add(WordData("тихий; все еще","still [stil]"))
        list.add(WordData("после, через; потом","after ['a:ftə]"))
        list.add(WordData("с , от","off [ɔv]"))
        list.add(WordData("имел, имела","has [hæz]"))
        list.add(WordData("никогда","never ['nevə]"))
        list.add(WordData("живущий, существующий, ходьба","going ['gəiŋ]"))
        list.add(WordData("даже; ровно","even ['i:v(ə)n]"))
        list.add(WordData("много","much [mʌtʃ]"))
        list.add(WordData("шёл, ехал","went [went]"))
        list.add(WordData("также; слишком","too [tu:]"))
        list.add(WordData("далеко; прочь","away [ə'wei]"))
        list.add(WordData("что-то, что-нибудь; примерно","something ['sʌmθiŋ]"))
        list.add(WordData("первый; сначала","first [fз:st]"))
        list.add(WordData("делать, производить; совершать","make [meik]"))
        list.add(WordData("голова","head [hed]"))
        list.add(WordData("хотеть","want [wɔnt]"))
        list.add(WordData("поворачивать(ся)","turn [tз:n]"))
        list.add(WordData("лицо","face [feis]"))
        list.add(WordData("сделан, сделанный","made [meid]"))
        list.add(WordData("казаться","seem [si:m]"))
        list.add(WordData("призыв; звать","call [kɔ:l]"))
        list.add(WordData("спрашивать","ask [a:sk]"))
        list.add(WordData("1) вспом. глагол; 2) должен, следует","should [ʃud]"))
        list.add(WordData("через , сквозь","through [θru:]"))
        list.add(WordData("длинный; долго","long [lɔŋ]"))
        list.add(WordData("позволять","let [let]"))
        list.add(WordData("брать; доставлять; принимать","take [teik]"))
        list.add(WordData("видел, пила","saw [sɔ:]"))
        list.add(WordData("кругом, вокруг; поблизости","around [ə'raund]"))
        list.add(WordData("наш","our ['auə]"))
        list.add(WordData("дверь","door [dɔ:]"))
        list.add(WordData("последний","last [la:st]"))
        list.add(WordData("говорить","tell [tel]"))
        list.add(WordData("мог, мог бы, энергия","might [mait]"))
        list.add(WordData("свой; владеть","own [əun]"))
        list.add(WordData("ночь; вечер","night [nait]"))
        list.add(WordData("год","year [jiə]"))
        list.add(WordData("должен, обязан","must [mʌst]"))
        list.add(WordData("потому что","because [bi:kɔz]"))
        list.add(WordData("голос","voice [vɔis]"))
        list.add(WordData("такой, тот, подобный","such [sʌtʃ]"))
        list.add(WordData("комната","room [ru:m]"))
        list.add(WordData("место; помещать","place [pleis]"))
        list.add(WordData("те","those [ðəuz]"))
        list.add(WordData("работа; работать","work [wз:k]"))
        list.add(WordData("положить","put [put]"))
        list.add(WordData("счёт, законопроект, клюв","bill [bil]"))
        list.add(WordData("ничего","nothing ['nʌθiŋ]"))
        list.add(WordData("самый большой, наибольший","most [məust]"))
        list.add(WordData("дом","house [haus]"))
        list.add(WordData("почему","why [(h)wai]"))
        list.add(WordData("мочь; май","may [mei]"))
        list.add(WordData("сторона","side [said]"))
        list.add(WordData("большой, великий","great [greit]"))
        list.add(WordData("эти","these [ði:z]"))
        list.add(WordData("белый","white [(h)wait]"))
        list.add(WordData("люди, нация, народ","people ['pi:pl]"))
        list.add(WordData("на","upon [ə'pɔn]"))
        list.add(WordData("левый, налево, покинул, уехал","left [left]"))
        list.add(WordData("открывать(ся)","open ['əup(ə)n]"))
        list.add(WordData("себя; сам","himself [him'self]"))
        list.add(WordData("друг","friend [frend]"))
        list.add(WordData("чувствовал, войлок","felt [felt]"))
        list.add(WordData("три","three [θri:]"))
        list.add(WordData("знал","knew [nju:]"))
        list.add(WordData("другой","another [ə'nʌðə]"))
        list.add(WordData("один раз, однажды, когда-то","once [wʌn(t)s]"))
        list.add(WordData("давать","give [giv]"))
        list.add(WordData("почти","almost ['ɔ:lməust]"))
        list.add(WordData("разум; мнение","mind [maind]"))
        list.add(WordData("брал, хватал","took [tuk]"))
        list.add(WordData("свет; освещать(ся)","light [lait]"))
        list.add(WordData("да","yes [jes]"))
        list.add(WordData("любовь, любить","love [lʌv]"))
        list.add(WordData("конец; кончать(ся)","end [end]"))
        list.add(WordData("мальчик, парень","boy [bɔi]"))
        list.add(WordData("смотреть","looking ['lukiŋ]"))
        list.add(WordData("в то время как","while [(h)wail]"))
        list.add(WordData("звук; звучать","sound [saund]"))
        list.add(WordData("миг, момент","moment ['məumənt]"))
        list.add(WordData("люди, мужчины","men [men]"))
        list.add(WordData("когда-либо","ever ['evə]"))
        list.add(WordData("под; ниже","under ['ʌndə]"))
        list.add(WordData("говорил, рассказывал","told [təuld]"))
        list.add(WordData("действительно","really ['riəli]"))
        list.add(WordData("жизнь","life [laif]"))
        list.add(WordData("мир, свет; вселенная","world [wз:ld]"))
        list.add(WordData("тот же самый","same [seim]"))
        list.add(WordData("уверенный; конечно","sure [ʃuə]"))
        list.add(WordData("новый","new [nju:]"))
        list.add(WordData("находил, встречал","found [faund]"))
        list.add(WordData("жизнь, существование","being ['bi:iŋ]"))
        list.add(WordData("достаточно","enough [i'nʌf]"))
        list.add(WordData("ушли, ушёл, ушедший","gone [gɔn]"))
        list.add(WordData("много, многие","many ['meni]"))
        list.add(WordData("большой","big [big]"))
        list.add(WordData("3-е л. ед. наст. времени от do","does [dʌz]"))
        list.add(WordData("каждый, всякий","every ['evri]"))
        list.add(WordData("начинал, начиналось","began [bi'gæn]"))
        list.add(WordData("всегда","always ['ɔ:lweiz]"))
        list.add(WordData("девочка, девушка","girl [gз:l]"))
        list.add(WordData("дом","home [həum]"))
        list.add(WordData("без, в отсутствие","without [wi'ðaut]"))
        list.add(WordData("слышал, услышал","heard [h:зd]"))
        list.add(WordData("к","toward(s) [tə'wɔ:dz]"))
        list.add(WordData("нуждаться","need [ni:d]"))
        list.add(WordData("остановка; останавливать(ся)","stop [stɔp]"))
        list.add(WordData("может быть","maybe ['meibi]"))
        list.add(WordData("доля, часть, отчасти","part [pa:t]"))
        list.add(WordData("применение, польза; употреблять","use [ju:z]"))
        list.add(WordData("хорошо!, ладно!, есть!","okay или OK [əu'kei]"))
        list.add(WordData("использовать, применять","use [ju:z]"))
        list.add(WordData("хотя, даже, тем не менее","though [ðəu]"))
        list.add(WordData("дальний; далеко","far [fa:]"))
        list.add(WordData("имя; название","name [neim]"))
        list.add(WordData("слово","word [wз:d]"))
        list.add(WordData("за; сзади , позади","behind [bi'haind]"))
        list.add(WordData("пытаться, пробовать","try [trai]"))
        list.add(WordData("помощь; помогать","help [help]"))
        list.add(WordData("тоже, также","also ['ɔ:lsəu]"))
        list.add(WordData("лучший, наилучший","better ['betə]"))
        list.add(WordData("середина; значить; подразумевать","mean [mi:n]"))
        list.add(WordData("отец","father ['fa:ðə]"))
        list.add(WordData("против","against [ə'gen(t)st]"))
        list.add(WordData("что-нибудь; что угодно","anything ['eniθiŋ]"))
        list.add(WordData("начало; начинать(ся)","start [sta:t]"))
        list.add(WordData("ещё, всё ещё","yet [jet]"))
        list.add(WordData("ходьба; ходить","walk [wɔ:k]"))
        list.add(WordData("женщина","woman ['wumən]"))
        list.add(WordData("видел","seen [si:n]"))
        list.add(WordData("близкий, тесный; близко; закрывать(ся)","close [kləuz]"))
        list.add(WordData("помнить, вспоминать","remember [ri'membə]"))
        list.add(WordData("автомобиль, машина","car [ka:]"))
        list.add(WordData("между","between [bi'twi:n]"))
        list.add(WordData("до, (до тех пор) пока (не)","until [(ə)n'til]"))
        list.add(WordData("оба","both [bəuθ]"))
        list.add(WordData("сделанный; выполненный","done [dʌn]"))
        list.add(WordData("бог","god [gɔd]"))
        list.add(WordData("находить, обнаруживать","find [faind]"))
        list.add(WordData("вода","water ['wɔ:tə]"))
        list.add(WordData("рука;","arm [a:m]"))
        list.add(WordData("немного; a ~ несколько","few [fju:]"))
        list.add(WordData("слышать; слушать","hear [hiə]"))

        for (w in list)
            dbHlp.addWord(w)
    }

    var FILE_SELECT_CODE : Int = 0;
    fun loadFromFile() {
        var intent = Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (ex : Exception) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            val dbHelp = dbHelper(this, null)
            var added:Int=0
            for (line in File(data?.data?.path?.split(":")?.get(1)).readLines()) {
                val w = line.split(";")
                if (w.size<2) continue
                val r = dbHelp.addWord(WordData(w[1],w[0]))
                if (r==dbHelper.ADD_STATUS.ADDED)
                    added++
            }
            Toast.makeText(this, "Добалено $added слов", Toast.LENGTH_SHORT)
        } catch (e : Exception){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT)
        }
    }

    fun setupPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1)
            }
        } else {
        }
    }

    fun onClickloadFromFile(view: View) {
        setupPermissions()
        loadFromFile()

    }
}
