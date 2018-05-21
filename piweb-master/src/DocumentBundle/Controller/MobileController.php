<?php
/**
 * Created by PhpStorm.
 * User: Manai
 * Date: 25/04/2018
 * Time: 00:10
 */

namespace DocumentBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use UtilisateurBundle\Entity\Document;
use UtilisateurBundle\Entity\User;

class MobileController extends Controller
{
    public function ajouterAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $doc = new Document();
        $doc->setEtat("en attente");
        $doc->setTitre($request->get('titre'));
        $doc->setDescription($request->get('description'));
        $doc->setNiveau($request->get('niveau'));
        $doc->setMatiere($request->get('matiere'));
        $doc->setPath($request->get('path'));
        $user = $em->getRepository(User::class)->find($request->get('idUser'));
        $doc->setIdUser($user);
        $em->persist($doc);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($doc);
        return new JsonResponse($formatted);
    }

    public function listAction()
    {
        $docs = $this->getDoctrine()->getRepository("UtilisateurBundle:Document")->findByEtat('publique');
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $data = $serializer->normalize($docs);
        return new JsonResponse($data);
    }

    public function findAction($id)
    {
        $doc = $this->getDoctrine()->getManager()->getRepository("UtilisateurBundle:Document")->find($id);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($doc);
        return new JsonResponse($formatted);

    }

    public function ImagesAction(Request $request)
    {
        $publicResourcesFolderPath = $this->get('kernel')->getRootDir() . '/../web/uploads/';
        $image = $request->query->get("img");

        // This should return the file located in /mySymfonyProject/web/public-resources/TextFile.txt
        // to being viewed in the Browser
        return new BinaryFileResponse($publicResourcesFolderPath.$image);
    }

    public function listeattenteAction($id)
    {
        $doc = $this->getDoctrine()->getManager()->getRepository("UtilisateurBundle:Document")->findBy(array('idUser'=>$id, 'etat'=>'en attente'));
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($doc);
        return new JsonResponse($formatted);

    }

    public function supprimerAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $doc = $em->getRepository("UtilisateurBundle:Document")->find($id);
        $em->remove($doc);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($doc);
        return new JsonResponse($formatted);
    }


}